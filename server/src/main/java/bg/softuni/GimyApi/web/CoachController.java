package bg.softuni.GimyApi.web;

import bg.softuni.GimyApi.model.view.CoachViewModel;
import bg.softuni.GimyApi.service.CoachService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
