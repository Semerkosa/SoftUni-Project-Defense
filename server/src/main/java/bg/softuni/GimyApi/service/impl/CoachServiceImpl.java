package bg.softuni.GimyApi.service.impl;

import bg.softuni.GimyApi.model.entity.CoachEntity;
import bg.softuni.GimyApi.model.entity.CoachReviewEntity;
import bg.softuni.GimyApi.model.service.CoachServiceModel;
import bg.softuni.GimyApi.model.view.CoachViewModel;
import bg.softuni.GimyApi.repository.CoachRepository;
import bg.softuni.GimyApi.repository.CoachReviewRepository;
import bg.softuni.GimyApi.service.CoachService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
}
