package bg.softuni.GimyApi.web;

import bg.softuni.GimyApi.model.service.UserServiceModel;
import bg.softuni.GimyApi.model.view.UserViewModel;
import bg.softuni.GimyApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @CrossOrigin
    public UserViewModel registerUser(@RequestBody UserServiceModel userServiceModel) {
        System.out.println("New user registration attempt for user " + userServiceModel.getEmail());

        UserViewModel userViewModel = userService.registerUser(userServiceModel);

        System.out.println("Successful registration (" + userViewModel.getId() + ")");

        return userViewModel;
    }
}
