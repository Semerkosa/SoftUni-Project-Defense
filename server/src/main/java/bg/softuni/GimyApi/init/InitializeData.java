package bg.softuni.GimyApi.init;

import bg.softuni.GimyApi.model.service.UserRegisterServiceModel;
import bg.softuni.GimyApi.model.view.UserViewModel;
import bg.softuni.GimyApi.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitializeData implements CommandLineRunner {

    private final UserService userService;

    public InitializeData(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        if (userService.getUsersCount() > 0) {
            return;
        }

        System.out.println("Creating an admin user...");

        UserRegisterServiceModel user = new UserRegisterServiceModel("admin@abv.bg", "Pesho", "asd");

        UserViewModel userViewModel = userService.registerUser(user);

        System.out.println("Registered user -> " + userViewModel);
    }
}
