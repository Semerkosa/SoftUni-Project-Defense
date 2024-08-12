package bg.softuni.GimyApi.repository;

import bg.softuni.GimyApi.model.entity.CoachReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachReviewRepository extends JpaRepository<CoachReviewEntity, String> {


}
