package bg.softuni.GimyApi.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "coaches")
public class CoachEntity extends BaseEntity {

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "coach", fetch = FetchType.EAGER)
    private List<UserEntity> users;

    @OneToMany(mappedBy = "coach", fetch = FetchType.EAGER)
    private List<CoachReviewEntity> coachReviews;

    public CoachEntity() {
    }

    public String getFirstName() {
        return firstName;
    }

    public CoachEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CoachEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public CoachEntity setPrice(double price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CoachEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public CoachEntity setUsers(List<UserEntity> users) {
        this.users = users;
        return this;
    }

    public List<CoachReviewEntity> getCoachReviews() {
        return coachReviews;
    }

    public CoachEntity setCoachReviews(List<CoachReviewEntity> coachReviews) {
        this.coachReviews = coachReviews;
        return this;
    }

    @Override
    public String toString() {
        return "CoachEntity{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", users=" + users +
                ", coachReviews=" + coachReviews +
                '}';
    }
}
