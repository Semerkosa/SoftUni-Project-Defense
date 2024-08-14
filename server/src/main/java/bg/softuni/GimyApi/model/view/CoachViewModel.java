package bg.softuni.GimyApi.model.view;

import java.util.ArrayList;
import java.util.List;

public class CoachViewModel extends BaseViewModel {

    private String firstName;
    private String lastName;
    private String description;
    private double price;
    private List<String> reviews;
    private List<String> clients;

    public CoachViewModel() {
        reviews = new ArrayList<>();
        clients = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public CoachViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CoachViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CoachViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public CoachViewModel setPrice(double price) {
        this.price = price;
        return this;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public CoachViewModel setReviews(List<String> reviews) {
        this.reviews = reviews;
        return this;
    }

    public List<String> getClients() {
        return clients;
    }

    public CoachViewModel setClients(List<String> clients) {
        this.clients = clients;
        return this;
    }

    @Override
    public String toString() {
        return "CoachViewModel{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", reviews=" + reviews +
                ", clients=" + clients +
                '}';
    }
}
