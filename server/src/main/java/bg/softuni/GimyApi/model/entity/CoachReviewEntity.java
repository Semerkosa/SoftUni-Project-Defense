package bg.softuni.GimyApi.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "coach_reviews")
public class CoachReviewEntity extends BaseEntity {

    @Column(nullable = false, columnDefinition = "TEXT")
    private String review;

    @ManyToOne
    private CoachEntity coach;

    public CoachReviewEntity() {
    }

    public CoachReviewEntity(String review) {
        this.review = review;
    }

    public String getReview() {
        return review;
    }

    public CoachReviewEntity setReview(String review) {
        this.review = review;
        return this;
    }

    public CoachEntity getCoach() {
        return coach;
    }

    public CoachReviewEntity setCoach(CoachEntity coach) {
        this.coach = coach;
        return this;
    }
}
