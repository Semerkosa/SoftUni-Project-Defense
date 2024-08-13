package bg.softuni.GimyApi.service;

import bg.softuni.GimyApi.model.service.WorkoutProgramServiceModel;
import bg.softuni.GimyApi.model.view.WorkoutProgramViewModel;

import java.util.List;

public interface WorkoutProgramService {

    WorkoutProgramViewModel createWorkoutProgram(WorkoutProgramServiceModel workoutProgram);

    boolean addReview(String workoutProgramId, String review);

    List<WorkoutProgramViewModel> getAllWorkoutPrograms();

    WorkoutProgramViewModel getWorkoutProgramById(String workoutProgramId);

    boolean isUserAdmin(String jwtToken);

    boolean deleteProgramById(String workoutProgramId);

    WorkoutProgramViewModel editProgramById(String workoutProgramId, WorkoutProgramServiceModel workoutProgramServiceModel);
}
