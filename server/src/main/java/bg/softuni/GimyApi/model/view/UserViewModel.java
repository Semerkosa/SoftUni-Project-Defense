package bg.softuni.GimyApi.model.view;

import java.util.List;

public class UserViewModel extends BaseViewModel {

    private List<WorkoutProgramViewModel> workoutProgram;
    private CoachViewModel coach;

    public UserViewModel() {
    }

    public List<WorkoutProgramViewModel> getWorkoutProgram() {
        return workoutProgram;
    }

    public UserViewModel setWorkoutProgram(List<WorkoutProgramViewModel> workoutProgram) {
        this.workoutProgram = workoutProgram;
        return this;
    }

    public CoachViewModel getCoach() {
        return coach;
    }

    public UserViewModel setCoach(CoachViewModel coach) {
        this.coach = coach;
        return this;
    }
}
