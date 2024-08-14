package bg.softuni.GimyApi.web;

import bg.softuni.GimyApi.model.service.UserLoginServiceModel;
import bg.softuni.GimyApi.model.service.UserRegisterServiceModel;
import bg.softuni.GimyApi.model.view.ErrorViewModel;
import bg.softuni.GimyApi.model.view.UserLoginViewModel;
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

        UserLoginViewModel userLoginViewModel = userService.registerUser(userRegisterServiceModel);

        if (userLoginViewModel == null) {
            return new ResponseEntity<>(new ErrorViewModel("Email exists!"), HttpStatus.BAD_REQUEST);
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

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserDataById(@PathVariable("userId") String userId) {
        if (userId == null || userId.isEmpty()) {
            return new ResponseEntity<>(new ErrorViewModel("Invalid reference!"), HttpStatus.BAD_REQUEST);
        }

        UserViewModel viewModel = userService.getUserDataById(userId);

        if (viewModel == null) {
            return new ResponseEntity<>(new ErrorViewModel("User not found!"), HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(viewModel);
    }
}
