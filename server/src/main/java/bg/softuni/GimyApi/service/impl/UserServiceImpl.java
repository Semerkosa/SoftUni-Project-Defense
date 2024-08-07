package bg.softuni.GimyApi.service.impl;

import bg.softuni.GimyApi.model.entity.UserEntity;
import bg.softuni.GimyApi.model.service.UserServiceModel;
import bg.softuni.GimyApi.model.view.UserViewModel;
import bg.softuni.GimyApi.repository.UserRepository;
import bg.softuni.GimyApi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public long getUsersCount() {
        return userRepository.count();
    }

    @Override
    public UserViewModel registerUser(UserServiceModel userServiceModel) {
        UserEntity user = modelMapper.map(userServiceModel, UserEntity.class);

        user = userRepository.saveAndFlush(user);
        System.out.println("Saved user - " + user);

        return modelMapper.map(user, UserViewModel.class);
    }
}
