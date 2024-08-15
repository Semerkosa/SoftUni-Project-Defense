package bg.softuni.GimyApi.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "Welcome to Gimy - an application, created to help anyone get in the best shape of their life!";
    }
}
