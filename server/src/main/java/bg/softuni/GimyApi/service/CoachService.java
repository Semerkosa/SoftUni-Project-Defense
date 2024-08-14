package bg.softuni.GimyApi.service;

import bg.softuni.GimyApi.model.service.CoachServiceModel;
import bg.softuni.GimyApi.model.view.CoachViewModel;

import java.util.List;

public interface CoachService {

    CoachViewModel createCoach(CoachServiceModel coachServiceModel);

    boolean addReview(String coachId, String review);

    List<CoachViewModel> getAllCoaches();

    CoachViewModel getCoachById(String coachId);

    boolean hireCoach(String userId, String coachId);

    boolean cancelCoach(String userId, String coachId);
}
