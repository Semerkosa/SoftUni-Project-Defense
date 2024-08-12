package bg.softuni.GimyApi.web;

import bg.softuni.GimyApi.model.service.WorkoutProgramServiceModel;
import bg.softuni.GimyApi.model.view.WorkoutProgramViewModel;
import bg.softuni.GimyApi.service.WorkoutProgramService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/workout-programs")
public class WorkoutProgramController {

    private final WorkoutProgramService workoutProgramService;

    public WorkoutProgramController(WorkoutProgramService workoutProgramService) {
        this.workoutProgramService = workoutProgramService;
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
        boolean isAdmin = workoutProgramService.isUserAdmin(token);

        if (!isAdmin) {
            return new ResponseEntity<>("You don't have access to perform the action!", HttpStatus.UNAUTHORIZED);
        }

        WorkoutProgramViewModel workoutProgram = workoutProgramService.createWorkoutProgram(workoutProgramServiceModel);
        System.out.println("Program created!");

        return ResponseEntity.ok(workoutProgram);
    }
}
