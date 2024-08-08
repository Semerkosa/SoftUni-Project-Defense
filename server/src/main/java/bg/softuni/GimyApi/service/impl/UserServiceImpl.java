package bg.softuni.GimyApi.service.impl;

import bg.softuni.GimyApi.model.entity.UserEntity;
import bg.softuni.GimyApi.model.service.UserLoginServiceModel;
import bg.softuni.GimyApi.model.service.UserRegisterServiceModel;
import bg.softuni.GimyApi.model.view.UserViewModel;
import bg.softuni.GimyApi.repository.UserRepository;
import bg.softuni.GimyApi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
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

    private UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    private boolean userExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
