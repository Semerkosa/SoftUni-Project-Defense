package bg.softuni.GimyApi.model.service;

public class UserRegisterServiceModel {

    private String email;
    private String firstName;
    private String lastName;
    private String password;

    public UserRegisterServiceModel() {}

    public UserRegisterServiceModel(String email, String firstName, String password) {
        this.email = email;
        this.firstName = firstName;
        this.password = password;
    }

    public UserRegisterServiceModel(String email, String firstName, String lastName, String password) {
        this(email, firstName, password);
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegisterServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegisterServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterServiceModel setPassword(String password) {
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
