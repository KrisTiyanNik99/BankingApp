package app.wallet.service;

import app.transaction.model.Transaction;
import app.transaction.model.TransactionStatus;
import app.transaction.model.TransactionType;
import app.transaction.service.TransactionService;
import app.user.model.User;
import app.wallet.model.Wallet;
import app.wallet.model.WalletStatus;
import app.wallet.repository.WalletRepository;
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

    private static final String DEFAULT_USER_WALLET_BALANCE = "20.00";
    private static final String DEFAULT_WALLET_CURRENCY = "EUR";
    private static final String SMART_WALLET_PLATFORM = "SMART WALLET PLATFORM";
    private static final String TRANSACTIONAL_DESCRIPTION = "Top-up %.2f";
    private static final String INACTIVE_FAIL_TRANSACTION = "Inactive wallet";

    private final WalletRepository walletRepository;
    private final TransactionService transactionService;

    @Autowired
    public WalletService(WalletRepository walletRepository, TransactionService transactionService) {
        this.walletRepository = walletRepository;
        this.transactionService = transactionService;
    }

    @Transactional
    public Transaction topUp(UUID walletId, BigDecimal topUpAmount) {
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

    private Wallet getWalletById(UUID walletId) {
        return walletRepository.findById(walletId).orElseThrow(() -> new RuntimeException(NOT_EXISTED_WALLET.formatted(walletId)));
    }
}
