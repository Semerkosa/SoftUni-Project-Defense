package bg.softuni.GimyApi.model.view;

public class UserViewModel extends BaseViewModel {

    private WorkoutProgramViewModel workoutProgram;
    private CoachViewModel coach;

    public UserViewModel() {
    }

    public WorkoutProgramViewModel getWorkoutProgram() {
        return workoutProgram;
    }

    public UserViewModel setWorkoutProgram(WorkoutProgramViewModel workoutProgram) {
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
