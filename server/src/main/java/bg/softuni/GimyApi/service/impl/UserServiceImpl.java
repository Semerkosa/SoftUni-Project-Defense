package bg.softuni.GimyApi.service.impl;

import bg.softuni.GimyApi.model.entity.AuthorityEntity;
import bg.softuni.GimyApi.model.entity.UserEntity;
import bg.softuni.GimyApi.model.service.UserLoginServiceModel;
import bg.softuni.GimyApi.model.service.UserRegisterServiceModel;
import bg.softuni.GimyApi.model.view.UserViewModel;
import bg.softuni.GimyApi.repository.AuthorityRepository;
import bg.softuni.GimyApi.repository.UserRepository;
import bg.softuni.GimyApi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public long getUsersCount() {
        return userRepository.count();
    }

    @Override
    public UserViewModel registerUser(UserRegisterServiceModel userRegisterServiceModel) {
        if (userExists(userRegisterServiceModel.getEmail())) {
            return null;
        }

        UserEntity user = modelMapper.map(userRegisterServiceModel, UserEntity.class);

        String hashedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPass);

        AuthorityEntity userAuthority = authorityRepository.findByAuthority("USER");
        user.addRole(userAuthority);

        user = userRepository.saveAndFlush(user);
        System.out.println("Saved user - " + user);

        return modelMapper.map(user, UserViewModel.class);
    }

    @Override
    public UserViewModel loginUser(UserLoginServiceModel userLoginServiceModel) {
        UserEntity user = getUserByEmail(userLoginServiceModel.getEmail());

        if (user == null) {
            return null;
        }

        boolean isPasswordMatched = passwordEncoder.matches(userLoginServiceModel.getPassword(), user.getPassword());

        if (!isPasswordMatched) {
            return null; // TODO: Return different message
        }

        return modelMapper.map(user, UserViewModel.class);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public void createAdminUser(String id) {
        System.out.println("Setting ADMIN role to user with id " + id);

        AuthorityEntity adminAuthority = authorityRepository.findByAuthority("ADMIN");

        UserEntity adminUser = userRepository.findById(id).get();
        adminUser.addRole(adminAuthority);

        userRepository.saveAndFlush(adminUser);
    }

    private UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    private boolean userExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
