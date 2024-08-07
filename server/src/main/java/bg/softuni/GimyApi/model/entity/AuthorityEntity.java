package bg.softuni.GimyApi.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
}
