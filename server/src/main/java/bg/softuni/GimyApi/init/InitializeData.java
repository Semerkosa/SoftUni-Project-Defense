package bg.softuni.GimyApi.init;

import bg.softuni.GimyApi.model.service.UserRegisterServiceModel;
import bg.softuni.GimyApi.model.service.WorkoutProgramServiceModel;
import bg.softuni.GimyApi.model.view.UserLoginViewModel;
import bg.softuni.GimyApi.service.AuthorityService;
import bg.softuni.GimyApi.service.UserService;
import bg.softuni.GimyApi.service.WorkoutProgramService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitializeData implements CommandLineRunner {

    private final UserService userService;
    private final AuthorityService authorityService;
    private final WorkoutProgramService workoutProgramService;

    public InitializeData(UserService userService, AuthorityService authorityService, WorkoutProgramService workoutProgramService) {
        this.userService = userService;
        this.authorityService = authorityService;
        this.workoutProgramService = workoutProgramService;
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

        UserLoginViewModel userLoginViewModel = userService.registerUser(adminUser);
        userService.createAdminUser(userLoginViewModel.getId());

        System.out.println("Adding workout programs...");

        WorkoutProgramServiceModel workoutProgram = new WorkoutProgramServiceModel(
                "Ripped in 90 days",
                89.99,
                "This program will help you get ripped! Get leaner, lose fat and build muscle at the same time! You will be unrecognizable in only 90 days!",
                "Work out for three days, rest one. On Monday - Chest & Back. On Tuesday - Legs, Shoulders & Abs. On Wednesday - Bi's & Tri's. Rest for a day and repeat! Rest for 60-75s between sets."
        );

        String workoutProgramId = workoutProgramService.createWorkoutProgram(workoutProgram).getId();

        workoutProgramService.addReview(workoutProgramId, "I saw amazing results on the 2nd week!");
        workoutProgramService.addReview(workoutProgramId, "One month in and I've lost 25 pounds.");
        workoutProgramService.addReview(workoutProgramId, "My waist became so thin that my jeans started falling.");
    }
}
