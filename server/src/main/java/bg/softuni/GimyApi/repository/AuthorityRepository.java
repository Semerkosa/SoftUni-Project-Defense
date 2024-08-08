package bg.softuni.GimyApi.repository;

import bg.softuni.GimyApi.model.entity.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, String> {

    AuthorityEntity findByAuthority(String authority);
}
