package bg.softuni.GimyApi.web;

import bg.softuni.GimyApi.model.service.WorkoutProgramServiceModel;
import bg.softuni.GimyApi.model.view.CustomMessageViewModel;
import bg.softuni.GimyApi.model.view.ErrorViewModel;
import bg.softuni.GimyApi.model.view.WorkoutProgramViewModel;
import bg.softuni.GimyApi.service.UserService;
import bg.softuni.GimyApi.service.WorkoutProgramService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/workout-programs")
public class WorkoutProgramController {

    private final WorkoutProgramService workoutProgramService;
    private final UserService userService;

    public WorkoutProgramController(WorkoutProgramService workoutProgramService, UserService userService) {
        this.workoutProgramService = workoutProgramService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<WorkoutProgramViewModel> getAllWorkoutPrograms() {
        List<WorkoutProgramViewModel> workoutPrograms = workoutProgramService.getAllWorkoutPrograms();
        System.out.println("Workout programs size: " + workoutPrograms.size());

        return workoutPrograms;
    }

    @GetMapping("/{workoutProgramId}")
    public ResponseEntity<?> getWorkoutProgramById(@PathVariable("workoutProgramId") String workoutProgramId) {
        WorkoutProgramViewModel workoutProgram = workoutProgramService.getWorkoutProgramById(workoutProgramId);
        System.out.println("Workout program: " + workoutProgram);

        return ResponseEntity.ok(Objects.requireNonNullElse(workoutProgram, "No such workout program."));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createWorkoutProgram(@RequestBody WorkoutProgramServiceModel workoutProgramServiceModel,
                                                  @RequestHeader(name = "Authorization") String jwtToken) {
        String token = jwtToken.split("\\s+")[1];
        boolean isAdmin = userService.isUserAdmin(token);

        if (!isAdmin) {
            return new ResponseEntity<>("You don't have access to perform the action!", HttpStatus.UNAUTHORIZED);
        }

        WorkoutProgramViewModel workoutProgram = workoutProgramService.createWorkoutProgram(workoutProgramServiceModel);
        System.out.println("Program created!");

        return ResponseEntity.ok(workoutProgram);
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseWorkoutProgram(@RequestParam("userId") String userId,
                                                    @RequestParam("workoutProgramId") String workoutProgramId) {
        System.out.println("Param1: " + userId);
        System.out.println("Param2: " + workoutProgramId);

        if (userId == null || userId.isEmpty() || workoutProgramId == null || workoutProgramId.isEmpty()) {
            return new ResponseEntity<>(new ErrorViewModel("Invalid reference!"), HttpStatus.BAD_REQUEST);
        }

        boolean success = workoutProgramService.purchaseWorkoutProgram(userId, workoutProgramId);

        if (!success) {
            return new ResponseEntity<>(new ErrorViewModel("Invalid reference!"), HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(new CustomMessageViewModel("Successfully purchased the workout program."));
    }

    @DeleteMapping("/delete/{workoutProgramId}")
    public ResponseEntity<?> deleteWorkoutProgramById(@PathVariable("workoutProgramId") String workoutProgramId,
                                                      @RequestHeader(name = "Authorization") String jwtToken) {
        if (workoutProgramId == null || workoutProgramId.isEmpty()) {
            return new ResponseEntity<>(new CustomMessageViewModel("Invalid reference"), HttpStatus.BAD_REQUEST);
        }

        String token = jwtToken.split("\\s+")[1];
        boolean isAdmin = userService.isUserAdmin(token);

        if (!isAdmin) {
            return new ResponseEntity<>(new CustomMessageViewModel("You don't have access to perform the action!"), HttpStatus.UNAUTHORIZED);
        }

        boolean success = workoutProgramService.deleteProgramById(workoutProgramId);

        if (!success) {
            return new ResponseEntity<>(new CustomMessageViewModel("Operation failed."), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(new CustomMessageViewModel("Workout program deleted!"));
    }

    @PatchMapping("/edit/{workoutProgramId}")
    public ResponseEntity<?> editWorkoutProgramById(@PathVariable("workoutProgramId") String workoutProgramId,
                                                    @RequestBody WorkoutProgramServiceModel workoutProgramServiceModel,
                                                    @RequestHeader(name = "Authorization") String jwtToken) {
        if (workoutProgramId == null || workoutProgramId.isEmpty()) {
            return new ResponseEntity<>(new CustomMessageViewModel("Invalid reference"), HttpStatus.BAD_REQUEST);
        }

        String token = jwtToken.split("\\s+")[1];
        boolean isAdmin = userService.isUserAdmin(token);

        if (!isAdmin) {
            return new ResponseEntity<>(new CustomMessageViewModel("You don't have access to perform the action!"), HttpStatus.UNAUTHORIZED);
        }

        WorkoutProgramViewModel viewModel = workoutProgramService.editProgramById(workoutProgramId, workoutProgramServiceModel);

        if (viewModel == null) {
            return new ResponseEntity<>(new CustomMessageViewModel("Workout program not found!"), HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(new CustomMessageViewModel("Workout program updated!"));
    }
}
