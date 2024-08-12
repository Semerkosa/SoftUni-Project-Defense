package bg.softuni.GimyApi.init;

import bg.softuni.GimyApi.model.service.CoachServiceModel;
import bg.softuni.GimyApi.model.service.UserRegisterServiceModel;
import bg.softuni.GimyApi.model.service.WorkoutProgramServiceModel;
import bg.softuni.GimyApi.model.view.UserLoginViewModel;
import bg.softuni.GimyApi.service.AuthorityService;
import bg.softuni.GimyApi.service.CoachService;
import bg.softuni.GimyApi.service.UserService;
import bg.softuni.GimyApi.service.WorkoutProgramService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitializeData implements CommandLineRunner {

    private final UserService userService;
    private final AuthorityService authorityService;
    private final WorkoutProgramService workoutProgramService;
    private final CoachService coachService;

    public InitializeData(UserService userService, AuthorityService authorityService, WorkoutProgramService workoutProgramService, CoachService coachService) {
        this.userService = userService;
        this.authorityService = authorityService;
        this.workoutProgramService = workoutProgramService;
        this.coachService = coachService;
    }

    @Override
    public void run(String... args) throws Exception {

        if (!authorityService.getAuthorities().isEmpty()) {
            return;
        }

        createRoles();
        createAdminUser();
        createWorkoutProgram();
        createCoach();
    }

    private void createCoach() {
        System.out.println("Creating coach...");

        CoachServiceModel coachServiceModel = new CoachServiceModel(
                "Dwayne",
                "Johnson",
                999.99,
                "Do you smell what The Rock is cooking? You heard me, pal! No introduction needed. You better be ready to feel the pain if you decide to hire me as your personal coach! I am not coming to terms with your bullshit excuses! You wanna look like a real fkin man? Hit the button and let's get started!"
        );

        System.out.println("Coach to create " + coachServiceModel);

        String coachId = coachService.createCoach(coachServiceModel).getId();

        System.out.println("Coach created!");

        System.out.println("Adding reviews to the coach...");

        // TODO: Check if all reviews are added correctly
        coachService.addReview(coachId, "My testosterone levels literally double just as I am speaking to him... Online!!!");
        coachService.addReview(coachId, "This man will make you go through HELL, but it is damn worth it!");
        coachService.addReview(coachId, "There is nothing to say really... You can't expect anything less than amazing results with such a man as your coach! What a proffessional!");
        coachService.addReview(coachId, "The Rock man... The one and only. Pure inspiration!");

        System.out.println("Reviews added!");
    }

    private void createWorkoutProgram() {
        System.out.println("Creating workout program...");

        WorkoutProgramServiceModel workoutProgram = new WorkoutProgramServiceModel(
                "Ripped in 90 days",
                89.99,
                "This program will help you get ripped! Get leaner, lose fat and build muscle at the same time! You will be unrecognizable in only 90 days!",
                "Work out for three days, rest one. On Monday - Chest & Back. On Tuesday - Legs, Shoulders & Abs. On Wednesday - Bi's & Tri's. Rest for a day and repeat! Rest for 60-75s between sets."
        );

        String workoutProgramId = workoutProgramService.createWorkoutProgram(workoutProgram).getId();

        System.out.println("Workout program created!");

        System.out.println("Adding reviews to the workout program...");

        // TODO: Check if all reviews are added correctly
        workoutProgramService.addReview(workoutProgramId, "I saw amazing results on the 2nd week!");
        workoutProgramService.addReview(workoutProgramId, "One month in and I've lost 25 pounds.");
        workoutProgramService.addReview(workoutProgramId, "My waist became so thin that my jeans started falling.");

        System.out.println("Reviews added!");
    }

    private void createAdminUser() {
        System.out.println("Creating an admin user...");

        UserRegisterServiceModel adminUser = new UserRegisterServiceModel("admin@abv.bg", "Admin", "Adminov", "Admin123");

        UserLoginViewModel userLoginViewModel = userService.registerUser(adminUser);
        userService.createAdminUser(userLoginViewModel.getId());

        System.out.println("Admin user created!");
    }

    private void createRoles() {
        System.out.println("Creating roles...");

        authorityService.initRoles();

        System.out.println("Roles created!");
    }
}
