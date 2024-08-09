package bg.softuni.GimyApi.model.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Table(name = "authorities")
public class AuthorityEntity extends BaseEntity implements GrantedAuthority {

    @Column(nullable = false)
    private String authority;

    @ManyToMany(mappedBy = "authorities")
    private List<UserEntity> users;

    @Override
    public String getAuthority() {
        return authority;
    }

    public AuthorityEntity() {
    }

    public AuthorityEntity(String authority) {
        this.authority = authority;
    }

    public AuthorityEntity setAuthority(String authority) {
        this.authority = authority;
        return this;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public AuthorityEntity setUsers(List<UserEntity> users) {
        this.users = users;
        return this;
    }

    @Override
    public String toString() {
        return "AuthorityEntity{" +
                "authority='" + authority + '\'' +
                '}';
    }
}
