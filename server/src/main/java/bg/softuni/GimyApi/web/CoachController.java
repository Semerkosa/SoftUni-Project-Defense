package bg.softuni.GimyApi.web;

import bg.softuni.GimyApi.model.view.CoachViewModel;
import bg.softuni.GimyApi.model.view.CustomMessageViewModel;
import bg.softuni.GimyApi.model.view.ErrorViewModel;
import bg.softuni.GimyApi.service.CoachService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coaches")
public class CoachController {

    private final CoachService coachService;

    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping("/all")
    public List<CoachViewModel> getAllCoaches() {
        List<CoachViewModel> coaches = coachService.getAllCoaches();
        System.out.println("Total coaches: " + coaches.size());

        return coaches;
    }

    @GetMapping("/{coachId}")
    public CoachViewModel getCoachById(@PathVariable String coachId) {
        CoachViewModel coach = coachService.getCoachById(coachId);
        System.out.println("Found the coach: " + coach);

        return coach;
    }

    @PostMapping("/hire")
    public ResponseEntity<?> hireCoach(@RequestParam("userId") String userId,
                                       @RequestParam("coachId") String coachId) {
        if (userId == null || userId.isEmpty() || coachId == null || coachId.isEmpty()) {
            return new ResponseEntity<>(new ErrorViewModel("Invalid reference!"), HttpStatus.BAD_REQUEST);
        }

        boolean success = coachService.hireCoach(userId, coachId);

        if (!success) {
            return new ResponseEntity<>(new ErrorViewModel("Invalid reference!"), HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(new CustomMessageViewModel("Successfully hired the coach."));
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancelCoach(@RequestParam("userId") String userId,
                                         @RequestParam("coachId") String coachId) {
        if (userId == null || userId.isEmpty() || coachId == null || coachId.isEmpty()) {
            return new ResponseEntity<>(new ErrorViewModel("Invalid reference!"), HttpStatus.BAD_REQUEST);
        }

        boolean success = coachService.cancelCoach(userId, coachId);

        if (!success) {
            return new ResponseEntity<>(new ErrorViewModel("Invalid reference!"), HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(new CustomMessageViewModel("Successfully cancelled the coach."));
    }
}
