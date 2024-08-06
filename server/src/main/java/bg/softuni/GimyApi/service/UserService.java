package bg.softuni.GimyApi.service;

import bg.softuni.GimyApi.model.service.UserServiceModel;
import bg.softuni.GimyApi.model.view.UserViewModel;

public interface UserService {

    long getUsersCount();

    UserViewModel registerUser(UserServiceModel userServiceModel);
}
