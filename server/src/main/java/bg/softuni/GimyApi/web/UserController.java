package bg.softuni.GimyApi.web;

import bg.softuni.GimyApi.model.service.UserLoginServiceModel;
import bg.softuni.GimyApi.model.service.UserRegisterServiceModel;
import bg.softuni.GimyApi.model.view.ErrorViewModel;
import bg.softuni.GimyApi.model.view.UserLoginViewModel;
import bg.softuni.GimyApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

        UserLoginViewModel userLoginViewModel = userService.registerUser(userRegisterServiceModel);

        if (userLoginViewModel == null) {
            // TODO: Handle better
            return ResponseEntity.ok(new ErrorViewModel("Email exists!"));
        }

        System.out.println("Successful registration (" + userLoginViewModel + ")");

        return ResponseEntity.ok(userLoginViewModel);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginServiceModel userLoginServiceModel) {
        System.out.println("Login attempt " + userLoginServiceModel.getEmail());

        UserLoginViewModel userLoginViewModel = userService.loginUser(userLoginServiceModel);

        if (userLoginViewModel == null) {
            // TODO: Handle better
            return ResponseEntity.ok(new ErrorViewModel("Invalid email or password!"));
        }

        System.out.println("Successful login");

        return ResponseEntity.ok(userLoginViewModel);
    }
}
