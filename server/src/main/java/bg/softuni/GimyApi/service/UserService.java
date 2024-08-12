package bg.softuni.GimyApi.service;

import bg.softuni.GimyApi.model.service.UserLoginServiceModel;
import bg.softuni.GimyApi.model.service.UserRegisterServiceModel;
import bg.softuni.GimyApi.model.view.UserLoginViewModel;

public interface UserService {

    long getUsersCount();

    UserLoginViewModel registerUser(UserRegisterServiceModel userRegisterServiceModel);

    UserLoginViewModel loginUser(UserLoginServiceModel userLoginServiceModel);

    void createAdminUser(String id);
}
