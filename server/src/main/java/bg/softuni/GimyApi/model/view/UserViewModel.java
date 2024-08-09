package bg.softuni.GimyApi.model.view;

public class UserViewModel extends BaseViewModel {

    private String token;
    private String firstName;
    private String lastName;

    public UserViewModel() {
    }

    public String getToken() {
        return token;
    }

    public UserViewModel setToken(String token) {
        this.token = token;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Override
    public String toString() {
        return "UserViewModel{" +
                "token='" + token + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
