package bg.softuni.GimyApi.service;

import bg.softuni.GimyApi.model.service.CoachServiceModel;
import bg.softuni.GimyApi.model.view.CoachViewModel;

public interface CoachService {

    CoachViewModel createCoach(CoachServiceModel coachServiceModel);

    boolean addReview(String coachId, String review);
}
