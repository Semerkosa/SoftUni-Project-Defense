package bg.softuni.GimyApi.service.impl;

import bg.softuni.GimyApi.model.entity.UserEntity;
import bg.softuni.GimyApi.model.service.UserServiceModel;
import bg.softuni.GimyApi.model.view.UserViewModel;
import bg.softuni.GimyApi.repository.UserRepository;
import bg.softuni.GimyApi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserViewModel registerUser(UserServiceModel userServiceModel) {
        UserEntity user = modelMapper.map(userServiceModel, UserEntity.class);

        String hashedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPass);

        user = userRepository.saveAndFlush(user);
        System.out.println("Saved user - " + user);

        return modelMapper.map(user, UserViewModel.class);
    }
}
