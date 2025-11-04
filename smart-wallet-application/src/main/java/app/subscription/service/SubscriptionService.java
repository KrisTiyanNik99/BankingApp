package app.subscription.service;

import app.subscription.model.Subscription;
import app.subscription.model.SubscriptionPeriod;
import app.subscription.model.SubscriptionStatus;
import app.subscription.model.SubscriptionType;
import app.subscription.repository.SubscriptionRepository;
import app.transaction.model.Transaction;
import app.transaction.model.TransactionStatus;
import app.user.model.User;
import app.wallet.service.WalletService;
import app.web.dto.UpgradeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class SubscriptionService {
    private static final String NO_ACTIVE_SUBSCRIPTION = "No active subscription!";
    private static final String SUBSCRIPTION_DESCRIPTION = "You by a new subscription";
    private static final String NO_SUBSCRIPTION_PERIOD = "Such subscription period does not exist!";

    private final SubscriptionRepository subscriptionRepository;
    private final WalletService walletService;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, WalletService walletService) {
        this.subscriptionRepository = subscriptionRepository;
        this.walletService = walletService;
    }

    public void createDefaultSubscription(User user) {
        Subscription defaultSubscription = Subscription.builder()
                .owner(user)
                .status(SubscriptionStatus.ACTIVE)
                .period(SubscriptionPeriod.MONTHLY)
                .type(SubscriptionType.DEFAULT)
                .price(BigDecimal.ZERO)
                .renewalAllowed(true)
                .createdOn(LocalDateTime.now())
                .completedOn(LocalDateTime.now().plusMonths(1))
                .build();

        subscriptionRepository.save(defaultSubscription);
    }


    public Transaction upgradeSubscription(User user, UpgradeRequest upgradeRequest, SubscriptionType subscriptionType) {
        Optional<Subscription> optSubscription = subscriptionRepository.findByStatusAndOwnerId(SubscriptionStatus.ACTIVE, user.getId());
        if (optSubscription.isEmpty()) {
            throw new RuntimeException(NO_ACTIVE_SUBSCRIPTION);
        }

        BigDecimal subscriptionPrice = getSubscriptionPrice(subscriptionType, upgradeRequest.getPeriod());
        Transaction subscriptionTransaction = walletService.charge(user, upgradeRequest.getWalletId(),
                subscriptionPrice, SUBSCRIPTION_DESCRIPTION);
        if (subscriptionTransaction.getStatus().equals(TransactionStatus.FAILED) && !subscriptionType.equals(SubscriptionType.DEFAULT)) {
            return subscriptionTransaction;
        }

        Subscription oldSubscription = optSubscription.get();
        oldSubscription.setStatus(SubscriptionStatus.COMPLETED);
        oldSubscription.setCompletedOn(LocalDateTime.now());

        LocalDateTime completedOn = (upgradeRequest.getPeriod().equals(SubscriptionPeriod.MONTHLY))
                ? LocalDateTime.now().plusMonths(1) : LocalDateTime.now().plusYears(1);
        completedOn.truncatedTo(ChronoUnit.DAYS);
        Subscription newSubscription = Subscription.builder()
                .owner(user)
                .status(SubscriptionStatus.ACTIVE)
                .period(upgradeRequest.getPeriod())
                .type(subscriptionType)
                .price(subscriptionPrice)
                .renewalAllowed(subscriptionType.equals(SubscriptionType.DEFAULT))
                .createdOn(LocalDateTime.now())
                .completedOn(completedOn)
                .build();

        subscriptionRepository.save(oldSubscription);
        subscriptionRepository.save(newSubscription);

        return subscriptionTransaction;
    }


    // ##########################           Helper methods           ###########################
    private BigDecimal getSubscriptionPrice(SubscriptionType subscriptionType, SubscriptionPeriod period) {
        if (subscriptionType.equals(SubscriptionType.DEFAULT)) {
            return BigDecimal.ZERO;
        } else if (subscriptionType.equals(SubscriptionType.PREMIUM) && period.equals(SubscriptionPeriod.MONTHLY)) {
            return new BigDecimal("19.99");
        } else if (subscriptionType.equals(SubscriptionType.PREMIUM) && period.equals(SubscriptionPeriod.YEARLY)) {
            return new BigDecimal("199.99");
        } else if (subscriptionType.equals(SubscriptionType.ULTIMATE) && period.equals(SubscriptionPeriod.MONTHLY)) {
            return new BigDecimal("49.99");
        } else if (subscriptionType.equals(SubscriptionType.ULTIMATE) && period.equals(SubscriptionPeriod.YEARLY)) {
            return new BigDecimal("499.99");
        }

        throw new RuntimeException(NO_SUBSCRIPTION_PERIOD);
    }
}