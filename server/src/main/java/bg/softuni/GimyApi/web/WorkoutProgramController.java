package bg.softuni.GimyApi.web;

import bg.softuni.GimyApi.model.view.WorkoutProgramViewModel;
import bg.softuni.GimyApi.service.WorkoutProgramService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
