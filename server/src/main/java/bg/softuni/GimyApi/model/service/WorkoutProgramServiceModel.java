package bg.softuni.GimyApi.model.service;

public class WorkoutProgramServiceModel {

    private String name;
    private double price;
    private String description;
    private String details;

    public WorkoutProgramServiceModel() {
    }

    public WorkoutProgramServiceModel(String name, double price, String description, String details) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.details = details;
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
}
