package bg.softuni.GimyApi.service.impl;

import bg.softuni.GimyApi.model.entity.*;
import bg.softuni.GimyApi.model.service.CoachServiceModel;
import bg.softuni.GimyApi.model.view.CoachViewModel;
import bg.softuni.GimyApi.model.view.WorkoutProgramViewModel;
import bg.softuni.GimyApi.repository.CoachRepository;
import bg.softuni.GimyApi.repository.CoachReviewRepository;
import bg.softuni.GimyApi.service.CoachService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CoachServiceImpl implements CoachService {

    private final CoachRepository coachRepository;
    private final CoachReviewRepository coachReviewRepository;

    private final ModelMapper modelMapper;

    public CoachServiceImpl(CoachRepository coachRepository, CoachReviewRepository coachReviewRepository, ModelMapper modelMapper) {
        this.coachRepository = coachRepository;
        this.coachReviewRepository = coachReviewRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CoachViewModel createCoach(CoachServiceModel coachServiceModel) {
        CoachEntity coach = modelMapper.map(coachServiceModel, CoachEntity.class);

        System.out.println("Coach entity " + coach);

        return modelMapper.map(coachRepository.saveAndFlush(coach), CoachViewModel.class);
    }

    @Override
    public boolean addReview(String coachId, String review) {
        CoachReviewEntity reviewEntity = new CoachReviewEntity(review);

        Optional<CoachEntity> coachEntity = coachRepository.findById(coachId);

        if (coachEntity.isEmpty()) {
            return false;
        }

        reviewEntity.setCoach(coachEntity.get());
        coachReviewRepository.saveAndFlush(reviewEntity);

        return true;
    }

    @Override
    public List<CoachViewModel> getAllCoaches() {
        List<CoachEntity> coaches = coachRepository.findAll();

        List<CoachViewModel> viewModels = new ArrayList<>();

        for (CoachEntity coach : coaches) {
            CoachViewModel viewModel = modelMapper.map(coach, CoachViewModel.class);

            List<String> usersHiredTheCoach = coach.getUsers()
                    .stream()
                    .map(UserEntity::getId)
                    .toList();

            viewModel.setClients(usersHiredTheCoach);

            List<String> reviews = coach.getCoachReviews()
                    .stream()
                    .map(CoachReviewEntity::getReview)
                    .toList();

            viewModel.setReviews(reviews);

            viewModels.add(viewModel);
        }

        return viewModels;
    }

    @Override
    public CoachViewModel getCoachById(String coachId) {
        Optional<CoachEntity> optionalCoach = coachRepository.findById(coachId);

        if (optionalCoach.isEmpty()) {
            return null;
        }

        CoachEntity coach = optionalCoach.get();

        CoachViewModel viewModel = modelMapper.map(coach, CoachViewModel.class);

        List<String> usersHiredTheCoach = coach.getUsers()
                .stream()
                .map(UserEntity::getId)
                .toList();

        viewModel.setClients(usersHiredTheCoach);

        List<String> reviews = coach.getCoachReviews()
                .stream()
                .map(CoachReviewEntity::getReview)
                .toList();

        viewModel.setReviews(reviews);

        return viewModel;
    }
}
