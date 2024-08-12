package bg.softuni.GimyApi.model.view;

import java.util.List;

public class UserLoginViewModel extends BaseViewModel {

    private String token;
    private String firstName;
    private String lastName;
    private List<String> authorities;

    public UserLoginViewModel() {
    }

    public String getToken() {
        return token;
    }

    public UserLoginViewModel setToken(String token) {
        this.token = token;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserLoginViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserLoginViewModel setLastName(String lastName) {
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
