package app.wallet.service;

import app.transaction.model.Transaction;
import app.transaction.model.TransactionStatus;
import app.transaction.model.TransactionType;
import app.transaction.service.TransactionService;
import app.user.model.User;
import app.wallet.model.Wallet;
import app.wallet.model.WalletStatus;
import app.wallet.repository.WalletRepository;
import app.web.dto.TransferRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.UUID;

@Service
public class WalletService {
    private static final String NOT_EXISTED_WALLET = "Wallet with [%s] not exist.";
    private static final String NO_ACTIVE_WALLET = "[%s] don't have any active wallets.";
    private static final String DEFAULT_USER_WALLET_BALANCE = "20.00";
    private static final String DEFAULT_WALLET_CURRENCY = "EUR";
    private static final String SMART_WALLET_PLATFORM = "SMART WALLET PLATFORM";
    private static final String TRANSACTIONAL_DESCRIPTION = "Top-up %.2f";
    private static final String INACTIVE_FAIL_TRANSACTION = "Inactive wallet";
    private static final String NOT_ENOUGH_AMOUNT = "Not enough amount in the wallet";
    private static final String INACTIVE_WALLET = "Wallet is inactive";
    private static final String NOT_OWNED_WALLET = "You don't own this wallet";
    private static final String TRANSFER_DESCRIPTION = "Transfer %s <> %s (%.2f)";

    private final WalletRepository walletRepository;
    private final TransactionService transactionService;

    @Autowired
    public WalletService(WalletRepository walletRepository, TransactionService transactionService) {
        this.walletRepository = walletRepository;
        this.transactionService = transactionService;
    }

    @Transactional
    public Transaction charge(User user, UUID walletId, BigDecimal amount, String description) {
        Wallet wallet = getWalletById(walletId);

        Transaction transaction = Transaction.builder()
                .owner(user)
                .sender(wallet.getId().toString())
                .receiver(SMART_WALLET_PLATFORM)
                .amount(amount)
                .currency(wallet.getCurrency())
                .type(TransactionType.WITHDRAWAL)
                .description(description)
                .createdOn(LocalDateTime.now())
                .build();

        if (!isWalletActive(wallet)) {
            transaction.setFailureReason(INACTIVE_WALLET);
            transaction.setStatus(TransactionStatus.FAILED);
        } else if (!hasSufficientFunds(wallet, amount)) {
            transaction.setFailureReason(NOT_ENOUGH_AMOUNT);
            transaction.setStatus(TransactionStatus.FAILED);
        } else if (!isWalletOwnedByUser(wallet, user)) {
            transaction.setFailureReason(NOT_OWNED_WALLET);
            transaction.setStatus(TransactionStatus.FAILED);
        } else {
            transaction.setStatus(TransactionStatus.SUCCEEDED);
            wallet.setBalance(wallet.getBalance().subtract(amount));
            wallet.setUpdatedOn(LocalDateTime.now());
            walletRepository.save(wallet);
        }

        transaction.setBalanceLeft(wallet.getBalance());
        return transactionService.upsert(transaction);
    }

    @Transactional
    public Transaction deposit(UUID walletId, BigDecimal topUpAmount) {
        Wallet wallet = getWalletById(walletId);

        // If wallet is Inactive transaction will be fail
        if (wallet.getStatus() == WalletStatus.INACTIVE) {
            return transactionService.createNewTransaction(wallet.getOwner(),
                    SMART_WALLET_PLATFORM,
                    wallet.getId().toString(),
                    topUpAmount,
                    wallet.getBalance(),
                    wallet.getCurrency(),
                    TransactionType.DEPOSIT,
                    TransactionStatus.FAILED,
                    TRANSACTIONAL_DESCRIPTION.formatted(topUpAmount.doubleValue()),
                    INACTIVE_FAIL_TRANSACTION);
        }

        wallet.setBalance(wallet.getBalance().add(topUpAmount));
        wallet.setUpdatedOn(LocalDateTime.now());

        walletRepository.save(wallet);

        return transactionService.createNewTransaction(wallet.getOwner(),
                SMART_WALLET_PLATFORM,
                wallet.getId().toString(),
                topUpAmount,
                wallet.getBalance(),
                wallet.getCurrency(),
                TransactionType.DEPOSIT,
                TransactionStatus.SUCCEEDED,
                TRANSACTIONAL_DESCRIPTION.formatted(topUpAmount.doubleValue()),
                null);
    }

    @Transactional
    public Transaction transfer(TransferRequest transferRequest) {
        Wallet sender = getWalletById(transferRequest.getWalletId());
        Wallet receiver = getFirstByUsername(transferRequest.getRecipientUsername());

        String transferDescription = TRANSFER_DESCRIPTION.formatted(sender.getOwner().getUsername(),
                receiver.getOwner().getUsername(), transferRequest.getAmount());
        Transaction withdraw = charge(sender.getOwner(), sender.getId(), transferRequest.getAmount(), transferDescription);
        if (withdraw.getStatus() == TransactionStatus.SUCCEEDED) {
            deposit(receiver.getId(), transferRequest.getAmount());
        }

        return withdraw;
    }

    public Wallet createNewWallet(User user) {
        Wallet newWallet = Wallet.builder()
                .owner(user)
                .status(WalletStatus.ACTIVE)
                .balance(new BigDecimal(DEFAULT_USER_WALLET_BALANCE))
                .currency(Currency.getInstance(DEFAULT_WALLET_CURRENCY))
                .createdOn(LocalDateTime.now())
                .updatedOn(LocalDateTime.now())
                .build();

        return walletRepository.save(newWallet);
    }


    // ##########################           Helper methods           ###########################
    private boolean isWalletActive(Wallet wallet) {
        return wallet.getStatus() == WalletStatus.ACTIVE;
    }

    private boolean isWalletOwnedByUser(Wallet wallet, User user) {
        return wallet.getOwner().getId().equals(user.getId());
    }

    private Wallet getWalletById(UUID walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new RuntimeException(NOT_EXISTED_WALLET.formatted(walletId)));
    }

    private boolean hasSufficientFunds(Wallet wallet, BigDecimal amount) {
        BigDecimal walletAmount = wallet.getBalance();
        BigDecimal hasAmount = amount;
        return walletAmount.compareTo(hasAmount) > 0;
    }

    private Wallet getFirstByUsername(String recipientUsername) {
        return walletRepository.findByOwnerUsername(recipientUsername)
                .stream()
                .filter(this::isWalletActive)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(NO_ACTIVE_WALLET.formatted(recipientUsername)));
    }
}