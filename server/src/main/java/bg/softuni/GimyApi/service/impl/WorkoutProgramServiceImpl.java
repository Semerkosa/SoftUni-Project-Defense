package bg.softuni.GimyApi.service.impl;

import bg.softuni.GimyApi.model.entity.UserEntity;
import bg.softuni.GimyApi.model.entity.WorkoutProgramEntity;
import bg.softuni.GimyApi.model.entity.WorkoutProgramReviewEntity;
import bg.softuni.GimyApi.model.service.WorkoutProgramServiceModel;
import bg.softuni.GimyApi.model.view.WorkoutProgramViewModel;
import bg.softuni.GimyApi.repository.WorkoutProgramRepository;
import bg.softuni.GimyApi.repository.WorkoutProgramReviewRepository;
import bg.softuni.GimyApi.service.UserService;
import bg.softuni.GimyApi.service.WorkoutProgramService;
import bg.softuni.GimyApi.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkoutProgramServiceImpl implements WorkoutProgramService {

    private final WorkoutProgramRepository workoutProgramRepository;
    private final WorkoutProgramReviewRepository workoutProgramReviewRepository;

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final JwtUtil jwtUtil;

    public WorkoutProgramServiceImpl(WorkoutProgramRepository workoutProgramRepository, WorkoutProgramReviewRepository workoutProgramReviewRepository, UserService userService, ModelMapper modelMapper, JwtUtil jwtUtil) {
        this.workoutProgramRepository = workoutProgramRepository;
        this.workoutProgramReviewRepository = workoutProgramReviewRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.jwtUtil = jwtUtil;
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

//        workoutProgram.get().addReview(reviewEntity);
//        workoutProgramRepository.saveAndFlush(workoutProgram.get());

//        System.out.println(workoutProgram.getWorkoutProgramReviews());
//        System.out.println(workoutProgramRepository.findById(workoutProgramId).get().getWorkoutProgramReviews());
        return true;
    }

    @Override
    public List<WorkoutProgramViewModel> getAllWorkoutPrograms() {
        List<WorkoutProgramEntity> workoutPrograms = workoutProgramRepository.findAll();

        List<WorkoutProgramViewModel> viewModels = new ArrayList<>();

        for (WorkoutProgramEntity workoutProgram : workoutPrograms) {
            WorkoutProgramViewModel viewModel = modelMapper.map(workoutProgram, WorkoutProgramViewModel.class);

            List<String> usersPurchasedTheProgram = workoutProgram.getUsers()
                    .stream()
                    .map(UserEntity::getId)
                    .toList();

            viewModel.setCustomers(usersPurchasedTheProgram);

            List<String> reviews = workoutProgram.getWorkoutProgramReviews()
                    .stream()
                    .map(WorkoutProgramReviewEntity::getReview)
                    .toList();

            viewModel.setReviews(reviews);

            viewModels.add(viewModel);
        }

        return viewModels;
    }

    @Override
    public WorkoutProgramViewModel getWorkoutProgramById(String workoutProgramId) {
        Optional<WorkoutProgramEntity> optionalWorkoutProgram = workoutProgramRepository.findById(workoutProgramId);

        if (optionalWorkoutProgram.isEmpty()) {
            return null;
        }

        WorkoutProgramEntity workoutProgram = optionalWorkoutProgram.get();

        WorkoutProgramViewModel viewModel = modelMapper.map(workoutProgram, WorkoutProgramViewModel.class);

        List<String> usersPurchasedTheProgram = workoutProgram.getUsers()
                .stream()
                .map(UserEntity::getId)
                .toList();

        viewModel.setCustomers(usersPurchasedTheProgram);

        List<String> reviews = workoutProgram.getWorkoutProgramReviews()
                .stream()
                .map(WorkoutProgramReviewEntity::getReview)
                .toList();

        viewModel.setReviews(reviews);

        return viewModel;
    }

    @Override
    public boolean isUserAdmin(String jwtToken) {
        String email = jwtUtil.extractEmail(jwtToken);
        return userService.isAdmin(email);
    }

}
