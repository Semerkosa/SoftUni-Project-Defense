package bg.softuni.GimyApi.repository;

import bg.softuni.GimyApi.model.entity.WorkoutProgramEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutProgramRepository extends JpaRepository<WorkoutProgramEntity, String> {
}
