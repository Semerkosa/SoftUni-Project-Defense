package bg.softuni.GimyApi.model.view;

import java.util.List;

public class WorkoutProgramViewModel extends BaseViewModel {

    private String name;
    private double price;
    private String description;
    private String details;
    private List<String> reviews;
    private List<String> customers;

    public WorkoutProgramViewModel() {
    }

    public String getName() {
        return name;
    }

    public WorkoutProgramViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public WorkoutProgramViewModel setPrice(double price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public WorkoutProgramViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public WorkoutProgramViewModel setDetails(String details) {
        this.details = details;
        return this;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public WorkoutProgramViewModel setReviews(List<String> reviews) {
        this.reviews = reviews;
        return this;
    }

    public List<String> getCustomers() {
        return customers;
    }

    public WorkoutProgramViewModel setCustomers(List<String> customers) {
        this.customers = customers;
        return this;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
