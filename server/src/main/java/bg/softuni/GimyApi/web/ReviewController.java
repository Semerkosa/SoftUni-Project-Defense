package bg.softuni.GimyApi.web;

import bg.softuni.GimyApi.model.view.CustomMessageViewModel;
import bg.softuni.GimyApi.service.WorkoutProgramService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final WorkoutProgramService workoutProgramService;

    public ReviewController(WorkoutProgramService workoutProgramService) {
        this.workoutProgramService = workoutProgramService;
    }

    @PostMapping("/post/workout-program")
    public ResponseEntity<?> postWorkoutProgramReview(@RequestBody String review) {
//        return ResponseEntity.ok(new CustomMessageViewModel("test"));
        return new ResponseEntity<>("kur", HttpStatus.NOT_FOUND);
    }
}
