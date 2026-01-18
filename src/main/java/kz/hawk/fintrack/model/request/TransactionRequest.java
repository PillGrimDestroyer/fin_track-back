package kz.hawk.fintrack.model.request;


import kz.hawk.fintrack.model.enums.Transaction;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author megam
 * @since 18.01.2026 8:05
 */
@Data
public class TransactionRequest {

  @NotNull(message = "'amount' is required")
  @Positive(message = "'amount' must be a positive number (greater than zero)")
  private BigDecimal amount;

  @NotNull(message = "'type' is required")
  private Transaction type;

  private String description;

  @NotNull(message = "'transactionDate' is required")
  private LocalDateTime transactionDate;

  @NotNull(message = "'categoryId' is required")
  private UUID categoryId;

}
