package bg.softuni.GimyApi.repository;

import bg.softuni.GimyApi.model.entity.WorkoutProgramReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutProgramReviewRepository extends JpaRepository<WorkoutProgramReviewEntity, String> {


}
