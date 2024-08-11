package bg.softuni.GimyApi.model.view;

import java.util.List;

public class UserViewModel extends BaseViewModel {

    private String token;
    private String firstName;
    private String lastName;
    private List<String> authorities;

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

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "UserViewModel{" +
                "token='" + token + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
