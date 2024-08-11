package bg.softuni.GimyApi.model.service;

import org.hibernate.mapping.TableOwner;

public class UserLoginServiceModel {

    private String email;
    private String password;

    public UserLoginServiceModel() {

    }

    public String getEmail() {
        return email;
    }

    public UserLoginServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "UserLoginServiceModel{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
