package bg.softuni.GimyApi.service;

import bg.softuni.GimyApi.model.service.WorkoutProgramServiceModel;
import bg.softuni.GimyApi.model.view.WorkoutProgramViewModel;

public interface WorkoutProgramService {

    WorkoutProgramViewModel createWorkoutProgram(WorkoutProgramServiceModel workoutProgram);

    boolean addReview(String workoutProgramId, String review);
}
