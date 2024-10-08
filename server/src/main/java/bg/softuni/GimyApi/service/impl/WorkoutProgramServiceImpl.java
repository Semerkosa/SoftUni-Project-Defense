package bg.softuni.GimyApi.service.impl;

import bg.softuni.GimyApi.exceptions.InvalidUserException;
import bg.softuni.GimyApi.model.entity.UserEntity;
import bg.softuni.GimyApi.model.entity.WorkoutProgramEntity;
import bg.softuni.GimyApi.model.entity.WorkoutProgramReviewEntity;
import bg.softuni.GimyApi.model.service.ReviewServiceModel;
import bg.softuni.GimyApi.model.service.WorkoutProgramServiceModel;
import bg.softuni.GimyApi.model.view.WorkoutProgramViewModel;
import bg.softuni.GimyApi.repository.UserRepository;
import bg.softuni.GimyApi.repository.WorkoutProgramRepository;
import bg.softuni.GimyApi.repository.WorkoutProgramReviewRepository;
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
    // Another solution is to inject the UserService, but we will have to lazy load it to avoid circular exception
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;
    private final JwtUtil jwtUtil;

    public WorkoutProgramServiceImpl(WorkoutProgramRepository workoutProgramRepository, WorkoutProgramReviewRepository workoutProgramReviewRepository, UserRepository userRepository, ModelMapper modelMapper, JwtUtil jwtUtil) {
        this.workoutProgramRepository = workoutProgramRepository;
        this.workoutProgramReviewRepository = workoutProgramReviewRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public WorkoutProgramViewModel createWorkoutProgram(WorkoutProgramServiceModel workoutProgramServiceModel) {
        WorkoutProgramEntity workoutProgram = modelMapper.map(workoutProgramServiceModel, WorkoutProgramEntity.class);

        WorkoutProgramViewModel viewModel = modelMapper.map(workoutProgramRepository.saveAndFlush(workoutProgram), WorkoutProgramViewModel.class);

        viewModel.setReviews(new ArrayList<>());
        viewModel.setCustomers(new ArrayList<>());

        return viewModel;
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
    public List<WorkoutProgramViewModel> getWorkoutProgramViewModels(List<WorkoutProgramEntity> workoutPrograms) {
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
    public List<WorkoutProgramViewModel> getAllWorkoutPrograms() {
        List<WorkoutProgramEntity> workoutPrograms = workoutProgramRepository.findAll();
        return getWorkoutProgramViewModels(workoutPrograms);
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
    public boolean deleteProgramById(String workoutProgramId) {
        Optional<WorkoutProgramEntity> optionalWorkoutProgram = workoutProgramRepository.findById(workoutProgramId);

        // Invalid id
        if (optionalWorkoutProgram.isEmpty()) {
            System.out.println("Invalid id for deletion!");
            return false;
        }

        workoutProgramRepository.delete(optionalWorkoutProgram.get());

        System.out.println("Delete operation executed!");

        // Tries to get the deleted entity. If it fails, then our deletion has been successful
        optionalWorkoutProgram = workoutProgramRepository.findById(workoutProgramId);

        return optionalWorkoutProgram.isEmpty();
    }

    @Override
    public WorkoutProgramViewModel editProgramById(String workoutProgramId, WorkoutProgramServiceModel workoutProgramServiceModel) {
        Optional<WorkoutProgramEntity> optionalWorkoutProgram = workoutProgramRepository.findById(workoutProgramId);

        // Invalid id
        if (optionalWorkoutProgram.isEmpty()) {
            System.out.println("Workout program not found with id " + workoutProgramId);
            return null;
        }

        WorkoutProgramEntity workoutProgram = optionalWorkoutProgram.get();

        workoutProgram.setName(workoutProgramServiceModel.getName());
        workoutProgram.setDescription(workoutProgramServiceModel.getDescription());
        workoutProgram.setDetails(workoutProgramServiceModel.getDetails());
        workoutProgram.setPrice(workoutProgramServiceModel.getPrice());

        return modelMapper.map(
                workoutProgramRepository.saveAndFlush(workoutProgram),
                WorkoutProgramViewModel.class
        );
    }

    @Override
    public boolean purchaseWorkoutProgram(String userId, String workoutProgramId) {
        Optional<UserEntity> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            return false;
        }

        Optional<WorkoutProgramEntity> optionalWorkoutProgram = workoutProgramRepository.findById(workoutProgramId);

        if (optionalWorkoutProgram.isEmpty()) {
            return false;
        }

        UserEntity userEntity = optionalUser.get();
        WorkoutProgramEntity workoutProgram = optionalWorkoutProgram.get();

        userEntity.addWorkoutProgram(workoutProgram);

        userRepository.saveAndFlush(userEntity);

        return true;
    }

    @Override
    public boolean postReview(ReviewServiceModel reviewServiceModel) throws InvalidUserException {
//        if (true) throw new InvalidUserException("The user must own the program to post reviews!");

        String userId = reviewServiceModel.getUserId();
        String workoutProgramId = reviewServiceModel.getEntityId();

        if (workoutProgramId == null || workoutProgramId.isEmpty() || userId == null || userId.isEmpty()) {
            System.out.println("Empty workout program id or user id");
            return false;
        }

        Optional<WorkoutProgramEntity> optionalWorkoutProgram = workoutProgramRepository.findById(workoutProgramId);
        Optional<UserEntity> optionalUser = userRepository.findById(userId);

        if (optionalWorkoutProgram.isEmpty() || optionalUser.isEmpty()) {
            System.out.println("No such workout program");
            return false;
        }

        WorkoutProgramEntity workoutProgram = optionalWorkoutProgram.get();
        UserEntity user = optionalUser.get();

        if (!workoutProgram.getUsers().contains(user)) {
            throw new InvalidUserException("The user must own the program to post reviews!");
        }

        WorkoutProgramReviewEntity reviewEntity = new WorkoutProgramReviewEntity(reviewServiceModel.getReview());
        reviewEntity.setWorkoutProgram(workoutProgram);

        workoutProgramReviewRepository.saveAndFlush(reviewEntity);

        return true;
    }
}
