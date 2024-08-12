package bg.softuni.GimyApi.service.impl;

import bg.softuni.GimyApi.model.entity.WorkoutProgramEntity;
import bg.softuni.GimyApi.model.entity.WorkoutProgramReviewEntity;
import bg.softuni.GimyApi.model.service.WorkoutProgramServiceModel;
import bg.softuni.GimyApi.model.view.WorkoutProgramViewModel;
import bg.softuni.GimyApi.repository.WorkoutProgramRepository;
import bg.softuni.GimyApi.repository.WorkoutProgramReviewRepository;
import bg.softuni.GimyApi.service.WorkoutProgramService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public WorkoutProgramViewModel createWorkoutProgram(WorkoutProgramServiceModel workoutProgramServiceModel) {
        WorkoutProgramEntity workoutProgram = modelMapper.map(workoutProgramServiceModel, WorkoutProgramEntity.class);

        return modelMapper.map(workoutProgramRepository.saveAndFlush(workoutProgram), WorkoutProgramViewModel.class);
    }

    @Override
    public boolean addReview(String workoutProgramId, String review) {
        WorkoutProgramReviewEntity reviewEntity = new WorkoutProgramReviewEntity(review);

        Optional<WorkoutProgramEntity> workoutProgram = workoutProgramRepository.findById(workoutProgramId);

        if (workoutProgram.isEmpty()) {
            return false;
        }

        reviewEntity.setWorkoutProgram(workoutProgram.get());
        workoutProgramReviewRepository.saveAndFlush(reviewEntity);

//        workoutProgram.addReview(reviewEntity);
//
//        workoutProgramRepository.saveAndFlush(workoutProgram);

//        System.out.println(workoutProgram.getWorkoutProgramReviews());
//        System.out.println(workoutProgramRepository.findById(workoutProgramId).get().getWorkoutProgramReviews());
        return true;
    }

}
