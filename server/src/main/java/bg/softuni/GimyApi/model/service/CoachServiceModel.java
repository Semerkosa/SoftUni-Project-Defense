package bg.softuni.GimyApi.model.service;

public class CoachServiceModel {

    private String firstName;
    private String lastName;
    private double price;
    private String description;

    public CoachServiceModel() {
    }

    public CoachServiceModel(String firstName, String lastName, double price, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.price = price;
        this.description = description;
    }

    public String getFirstName() {
        return firstName;
    }

    public CoachServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CoachServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public CoachServiceModel setPrice(double price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CoachServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "CoachServiceModel{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
