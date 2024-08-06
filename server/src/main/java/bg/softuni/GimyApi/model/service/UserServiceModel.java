package bg.softuni.GimyApi.model.service;

public class UserServiceModel {

    private String email;
    private String firstName;
    private String lastName;
    private String password;

    public UserServiceModel(String email, String firstName, String password) {
        this.email = email;
        this.firstName = firstName;
        this.password = password;
    }

    public UserServiceModel(String email, String firstName, String lastName, String password) {
        this(email, firstName, password);
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public UserServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "UserServiceModel{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
