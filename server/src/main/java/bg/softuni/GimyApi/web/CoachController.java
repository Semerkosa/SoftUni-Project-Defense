package bg.softuni.GimyApi.web;

import bg.softuni.GimyApi.model.view.CoachViewModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coaches")
public class CoachController {

    @GetMapping("/all")
    public List<CoachViewModel> getAllCoaches() {
        return List.of();
    }
}
