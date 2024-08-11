package bg.softuni.GimyApi.service;

import bg.softuni.GimyApi.model.service.WorkoutProgramServiceModel;

public interface WorkoutProgramService {

    String createWorkoutProgram(WorkoutProgramServiceModel workoutProgram);

    void addReview(String workoutProgramId, String review);
}
