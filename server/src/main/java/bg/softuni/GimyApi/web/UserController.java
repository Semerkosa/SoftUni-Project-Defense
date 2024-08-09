package bg.softuni.GimyApi.web;

import bg.softuni.GimyApi.model.service.UserLoginServiceModel;
import bg.softuni.GimyApi.model.service.UserRegisterServiceModel;
import bg.softuni.GimyApi.model.view.CustomMessageViewModel;
import bg.softuni.GimyApi.model.view.ErrorViewModel;
import bg.softuni.GimyApi.model.view.UserViewModel;
import bg.softuni.GimyApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterServiceModel userRegisterServiceModel) {
        System.out.println("New user registration attempt for user " + userRegisterServiceModel.getEmail());

        UserViewModel userViewModel = userService.registerUser(userRegisterServiceModel);

        if (userViewModel == null) {
            // TODO: Handle better
            return new ResponseEntity<>(new ErrorViewModel("Email exists!"), HttpStatus.OK);
        }

        System.out.println("Successful registration (" + userViewModel.getId() + ")");

        return new ResponseEntity<>(userViewModel, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginServiceModel userLoginServiceModel) {
        System.out.println("Login attempt " + userLoginServiceModel.getEmail());

        UserViewModel userViewModel = userService.loginUser(userLoginServiceModel);

        if (userViewModel == null) {
            // TODO: Handle better
            return new ResponseEntity<>(new ErrorViewModel("Invalid email or password!"), HttpStatus.OK);
        }

        System.out.println("Successful login (" + userViewModel.getId() + ")");

        return new ResponseEntity<>(userViewModel, HttpStatus.OK);
    }
}
