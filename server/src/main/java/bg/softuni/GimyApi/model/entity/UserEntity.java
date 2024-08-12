package bg.softuni.GimyApi.model.entity;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity implements UserDetails {

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Column(nullable = false, name = "date_time", updatable = false, insertable = false, columnDefinition = "datetime DEFAULT current_timestamp()")
    private LocalDateTime datetime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private List<AuthorityEntity> authorities;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_workout_programs",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "workout_program_id"))
    private List<WorkoutProgramEntity> workoutPrograms;

    @ManyToOne
    private CoachEntity coach;

    public UserEntity() {

    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public UserEntity setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
        return this;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", datetime=" + datetime +
                ", authorities=" + authorities +
                '}';
    }

    public List<AuthorityEntity> getAuthorities() {
        return authorities;
    }

    private UserEntity setAuthorities(List<AuthorityEntity> authorities) {
        this.authorities = authorities;
        return this;
    }

    public void addRole(AuthorityEntity authority) {
        List<AuthorityEntity> updatedAuthorities = getAuthorities() == null ? new ArrayList<>() : getAuthorities();
        updatedAuthorities.add(authority);

        this.setAuthorities(updatedAuthorities);
    }

    public List<WorkoutProgramEntity> getWorkoutPrograms() {
        return workoutPrograms;
    }

    public void setWorkoutPrograms(List<WorkoutProgramEntity> workoutPrograms) {
        this.workoutPrograms = workoutPrograms;
    }

    public CoachEntity getCoach() {
        return coach;
    }

    public UserEntity setCoach(CoachEntity coach) {
        this.coach = coach;
        return this;
    }
}
