package bg.softuni.GimyApi.web;

import bg.softuni.GimyApi.model.service.ReviewServiceModel;
import bg.softuni.GimyApi.model.view.CustomMessageViewModel;
import bg.softuni.GimyApi.service.WorkoutProgramService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final WorkoutProgramService workoutProgramService;

    public ReviewController(WorkoutProgramService workoutProgramService) {
        this.workoutProgramService = workoutProgramService;
    }

    @PostMapping("/post/workout-program")
    public ResponseEntity<?> postWorkoutProgramReview(@RequestBody ReviewServiceModel reviewServiceModel) {
        System.out.println(reviewServiceModel);
        boolean success = workoutProgramService.postReview(reviewServiceModel);

        if (!success) {
            return new ResponseEntity<>("Invalid reference!", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(new CustomMessageViewModel("Review successfully saved."));
    }
}
