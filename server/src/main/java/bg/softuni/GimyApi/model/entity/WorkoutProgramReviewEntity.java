package bg.softuni.GimyApi.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "workout_program_reviews")
public class WorkoutProgramReviewEntity extends BaseEntity {

    @Column(nullable = false, columnDefinition = "TEXT")
    private String review;

    @ManyToOne
    private WorkoutProgramEntity workoutProgram;

    public WorkoutProgramReviewEntity() {
    }

    public WorkoutProgramReviewEntity(String review) {
        this.review = review;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public WorkoutProgramEntity getWorkoutProgram() {
        return workoutProgram;
    }

    public void setWorkoutProgram(WorkoutProgramEntity workoutProgram) {
        this.workoutProgram = workoutProgram;
    }
}
