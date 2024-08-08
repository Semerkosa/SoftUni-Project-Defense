package bg.softuni.GimyApi.service;

import bg.softuni.GimyApi.model.service.UserLoginServiceModel;
import bg.softuni.GimyApi.model.service.UserRegisterServiceModel;
import bg.softuni.GimyApi.model.view.UserViewModel;

public interface UserService {

    long getUsersCount();

    UserViewModel registerUser(UserRegisterServiceModel userRegisterServiceModel);

    UserViewModel loginUser(UserLoginServiceModel userLoginServiceModel);

    void createAdminUser(String id);
}
