package bg.softuni.GimyApi.repository;

import bg.softuni.GimyApi.model.entity.CoachEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends JpaRepository<CoachEntity, String> {


}
