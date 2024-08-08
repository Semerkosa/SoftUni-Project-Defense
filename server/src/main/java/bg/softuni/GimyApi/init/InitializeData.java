package bg.softuni.GimyApi.init;

import bg.softuni.GimyApi.model.service.UserRegisterServiceModel;
import bg.softuni.GimyApi.model.view.UserViewModel;
import bg.softuni.GimyApi.service.AuthorityService;
import bg.softuni.GimyApi.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitializeData implements CommandLineRunner {

    private final UserService userService;
    private final AuthorityService authorityService;

    public InitializeData(UserService userService, AuthorityService authorityService) {
        this.userService = userService;
        this.authorityService = authorityService;
    }

    @Override
    public void run(String... args) throws Exception {

        if (!authorityService.getAuthorities().isEmpty()) {
            return;
        }

        System.out.println("Creating roles...");

        authorityService.initRoles();

        System.out.println("Creating an admin user...");

        UserRegisterServiceModel adminUser = new UserRegisterServiceModel("admin@abv.bg", "Admin", "Adminov", "Admin123");

        UserViewModel userViewModel = userService.registerUser(adminUser);
        userService.createAdminUser(userViewModel.getId());

        System.out.println("Registered user -> " + userViewModel);
    }
}
