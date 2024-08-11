package bg.softuni.GimyApi.service.impl;

import bg.softuni.GimyApi.model.entity.WorkoutProgramEntity;
import bg.softuni.GimyApi.model.entity.WorkoutProgramReviewEntity;
import bg.softuni.GimyApi.model.service.WorkoutProgramServiceModel;
import bg.softuni.GimyApi.repository.WorkoutProgramRepository;
import bg.softuni.GimyApi.repository.WorkoutProgramReviewRepository;
import bg.softuni.GimyApi.service.WorkoutProgramService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class WorkoutProgramServiceImpl implements WorkoutProgramService {

    private final WorkoutProgramRepository workoutProgramRepository;
    private final WorkoutProgramReviewRepository workoutProgramReviewRepository;

    private final ModelMapper modelMapper;

    public WorkoutProgramServiceImpl(WorkoutProgramRepository workoutProgramRepository, WorkoutProgramReviewRepository workoutProgramReviewRepository, ModelMapper modelMapper) {
        this.workoutProgramRepository = workoutProgramRepository;
        this.workoutProgramReviewRepository = workoutProgramReviewRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String createWorkoutProgram(WorkoutProgramServiceModel workoutProgramServiceModel) {
        WorkoutProgramEntity workoutProgram = modelMapper.map(workoutProgramServiceModel, WorkoutProgramEntity.class);

        WorkoutProgramEntity saved = workoutProgramRepository.saveAndFlush(workoutProgram);
        return saved.getId();
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public void addReview(String workoutProgramId, String review) {
        WorkoutProgramEntity workoutProgram = workoutProgramRepository.findById(workoutProgramId).get();

        WorkoutProgramReviewEntity reviewEntity = new WorkoutProgramReviewEntity(review);
        reviewEntity = workoutProgramReviewRepository.saveAndFlush(reviewEntity);

        workoutProgram.addReview(reviewEntity);

        workoutProgramRepository.saveAndFlush(workoutProgram);
    }

}
