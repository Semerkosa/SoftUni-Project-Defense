package bg.softuni.GimyApi.service.impl;

import bg.softuni.GimyApi.model.entity.AuthorityEntity;
import bg.softuni.GimyApi.model.entity.CoachEntity;
import bg.softuni.GimyApi.model.entity.UserEntity;
import bg.softuni.GimyApi.model.service.UserLoginServiceModel;
import bg.softuni.GimyApi.model.service.UserRegisterServiceModel;
import bg.softuni.GimyApi.model.view.CoachViewModel;
import bg.softuni.GimyApi.model.view.UserLoginViewModel;
import bg.softuni.GimyApi.model.view.UserViewModel;
import bg.softuni.GimyApi.model.view.WorkoutProgramViewModel;
import bg.softuni.GimyApi.repository.AuthorityRepository;
import bg.softuni.GimyApi.repository.UserRepository;
import bg.softuni.GimyApi.service.CoachService;
import bg.softuni.GimyApi.service.UserService;
import bg.softuni.GimyApi.service.WorkoutProgramService;
import bg.softuni.GimyApi.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    private final WorkoutProgramService workoutProgramService;
    private final CoachService coachService;

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository, WorkoutProgramService workoutProgramService, CoachService coachService, ModelMapper modelMapper, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.workoutProgramService = workoutProgramService;
        this.coachService = coachService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public long getUsersCount() {
        return userRepository.count();
    }

    @Override
    public UserLoginViewModel registerUser(UserRegisterServiceModel userRegisterServiceModel) {
        if (userExists(userRegisterServiceModel.getEmail())) {
            return null;
        }

        UserEntity user = modelMapper.map(userRegisterServiceModel, UserEntity.class);

        String hashedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPass);

        AuthorityEntity userAuthority = authorityRepository.findByAuthority("USER");
        user.addRole(userAuthority);

        user = userRepository.saveAndFlush(user);
        System.out.println("Saved user - " + user);

        return modelMapper.map(user, UserLoginViewModel.class);
    }

    @Override
    public UserLoginViewModel loginUser(UserLoginServiceModel userLoginServiceModel) {
        System.out.println("Authenticating user - " + userLoginServiceModel);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginServiceModel.getEmail(),
                        userLoginServiceModel.getPassword()
                )
        );

        System.out.println("Authenticated!");

        // We need the user's full name
        UserEntity user = getUserByEmail(userLoginServiceModel.getEmail());

        List<String> authorities = user.getAuthorities()
                .stream()
                .map(AuthorityEntity::getAuthority)
                .toList();

        String token = jwtUtil.generateToken(user);
        System.out.println("Generated Token: " + token);

        UserLoginViewModel viewModel = modelMapper.map(user, UserLoginViewModel.class);
        viewModel.setToken(token);
        viewModel.setAuthorities(authorities);

        return viewModel;
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public void createAdminUser(String id) {
        System.out.println("Setting ADMIN role to user with id " + id);

        AuthorityEntity adminAuthority = authorityRepository.findByAuthority("ADMIN");

        UserEntity adminUser = userRepository.findById(id).get();
        adminUser.addRole(adminAuthority);

        userRepository.saveAndFlush(adminUser);
    }

    @Override
    public boolean isAdmin(String email) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            return false;
        }

        UserEntity user = optionalUser.get();
        List<AuthorityEntity> userAuthorities = user.getAuthorities();

        AuthorityEntity adminAuthority = authorityRepository.findByAuthority("ADMIN");

        return userAuthorities.contains(adminAuthority);
    }

    @Override
    public boolean isUserAdmin(String jwtToken) {
        String email = jwtUtil.extractEmail(jwtToken);
        return isAdmin(email);
    }

    @Override
    public UserViewModel getUserDataById(String userId) {
        Optional<UserEntity> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            System.out.println("No such user with id " + userId);
            return null;
        }

        UserEntity userEntity = optionalUser.get();

        UserViewModel userViewModel = modelMapper.map(userEntity, UserViewModel.class);

        List<WorkoutProgramViewModel> workoutProgramViewModels = workoutProgramService
                .getWorkoutProgramViewModels(userEntity.getWorkoutPrograms());

        userViewModel.setWorkoutPrograms(workoutProgramViewModels);

        CoachEntity userCoach = userEntity.getCoach();

        userViewModel.setCoach(
                userCoach == null ?
                        null :
                        coachService.getCoachById(userCoach.getId())
        );

        return userViewModel;
    }

    private UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    private boolean userExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
