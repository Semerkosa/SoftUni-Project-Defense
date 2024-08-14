package bg.softuni.GimyApi.service;

import bg.softuni.GimyApi.model.entity.WorkoutProgramEntity;
import bg.softuni.GimyApi.model.service.WorkoutProgramServiceModel;
import bg.softuni.GimyApi.model.view.WorkoutProgramViewModel;

import java.util.List;

public interface WorkoutProgramService {

    WorkoutProgramViewModel createWorkoutProgram(WorkoutProgramServiceModel workoutProgram);

    boolean addReview(String workoutProgramId, String review);

    List<WorkoutProgramViewModel> getWorkoutProgramViewModels(List<WorkoutProgramEntity> workoutPrograms);

    List<WorkoutProgramViewModel> getAllWorkoutPrograms();

    WorkoutProgramViewModel getWorkoutProgramById(String workoutProgramId);

    boolean deleteProgramById(String workoutProgramId);

    WorkoutProgramViewModel editProgramById(String workoutProgramId, WorkoutProgramServiceModel workoutProgramServiceModel);

    boolean purchaseWorkoutProgram(String userId, String workoutProgramId);
}
