package app.user.service;

import app.subscription.service.SubscriptionService;
import app.user.model.User;
import app.user.model.UserRole;
import app.user.repository.UserRepository;
import app.wallet.service.WalletService;
import app.web.dto.LoginRequest;
import app.web.dto.RegisterRequest;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserService {
    private static final String NEW_USER_REGISTRATION_MESSAGE = "New user profile was registered in the system for user [%s].";
    private static final String USER_EXIST_ERROR_MESSAGE = "User with [%s] username already exist.";
    private static final String USER_DOES_NOT_EXIST = "Such user with [%s] does not exist!";
    private static final String INCORRECT_DATA = "Incorrect username or password.";
    private static final String USER_LOGIN_MESSAGE = "User [%s] login!";

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final SubscriptionService subscriptionService;
    private final WalletService walletService;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       SubscriptionService subscriptionService, WalletService walletService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.subscriptionService = subscriptionService;
        this.walletService = walletService;
    }

    @Transactional
    public void register(RegisterRequest registerRequest) {
        Optional<User> optionalUser = userRepository.findByUsername(registerRequest.getUsername());
        if (optionalUser.isPresent()) {
            throw new RuntimeException(USER_EXIST_ERROR_MESSAGE.formatted(registerRequest.getUsername()));
        }

        User user = User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .userRole(UserRole.USER)
                .country(registerRequest.getCountry())
                .active(true)
                .createdOn(LocalDateTime.now())
                .updatedOn(LocalDateTime.now())
                .build();

        userRepository.save(user);
        walletService.createNewWallet(user);
        subscriptionService.createDefaultSubscription(user);

        log.info(NEW_USER_REGISTRATION_MESSAGE.formatted(registerRequest.getUsername()));
    }

    public User login(LoginRequest loginRequest) {
        Optional<User> optionalUser = userRepository.findByUsername(loginRequest.getUsername());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException(USER_DOES_NOT_EXIST.formatted(loginRequest.getUsername()));
        }

        String rawPassword = loginRequest.getPassword();
        // Password check
        if (!passwordEncoder.matches(rawPassword, optionalUser.get().getPassword())) {
            throw new RuntimeException(INCORRECT_DATA);
        }

        log.info(USER_LOGIN_MESSAGE.formatted(loginRequest.getPassword()));

        return optionalUser.get();
    }

    public User getById(UUID userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Such user does not exit!");
        }

        return optionalUser.get();
    }
}