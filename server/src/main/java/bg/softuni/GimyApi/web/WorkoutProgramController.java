package bg.softuni.GimyApi.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workout-programs")
public class WorkoutProgramController {

    @GetMapping("/all")
    public String getAllWorkoutPrograms() {
        return "Programs";
    }
}
