package bg.softuni.GimyApi.model.view;

import java.util.List;

public class UserViewModel extends BaseViewModel {

    private List<WorkoutProgramViewModel> workoutPrograms;
    private CoachViewModel coach;

    public UserViewModel() {
    }

    public List<WorkoutProgramViewModel> getWorkoutPrograms() {
        return workoutPrograms;
    }

    public UserViewModel setWorkoutPrograms(List<WorkoutProgramViewModel> workoutPrograms) {
        this.workoutPrograms = workoutPrograms;
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
