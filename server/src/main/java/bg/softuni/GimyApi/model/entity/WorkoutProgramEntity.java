package bg.softuni.GimyApi.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workout-programs")
public class WorkoutProgramEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String details;

    @ManyToMany(mappedBy = "workoutPrograms")
    private List<UserEntity> users;

    @OneToMany(mappedBy = "workoutProgram", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<WorkoutProgramReviewEntity> workoutProgramReviews;

    public WorkoutProgramEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public List<WorkoutProgramReviewEntity> getWorkoutProgramReviews() {
        return workoutProgramReviews;
    }

    public void setWorkoutProgramReviews(List<WorkoutProgramReviewEntity> workoutProgramReviews) {
        this.workoutProgramReviews = workoutProgramReviews;
    }

    public void addReview(WorkoutProgramReviewEntity review) {
        List<WorkoutProgramReviewEntity> reviews = getWorkoutProgramReviews() == null ? new ArrayList<>() : getWorkoutProgramReviews();

        reviews.add(review);
        setWorkoutProgramReviews(reviews);
    }
}
