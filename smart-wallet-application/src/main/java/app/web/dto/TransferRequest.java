package app.web.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {
    private UUID walletId;
    private String recipientUsername;
    private BigDecimal amount;
}
