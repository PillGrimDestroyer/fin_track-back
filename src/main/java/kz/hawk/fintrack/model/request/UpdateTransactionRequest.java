package kz.hawk.fintrack.model.request;


import kz.hawk.fintrack.model.enums.Transaction;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * @author Work
 * @since 06.02.2026 9:18
 */
@Data
@NoArgsConstructor
public class UpdateTransactionRequest {

  @Positive(message = "'amount' must be a positive number (greater than zero)")
  private BigDecimal amount;

  private Transaction type;

  private String description;

  private OffsetDateTime transactionDate;

  private UUID categoryId;

  public UpdateTransactionRequest(UUID categoryId) {
    this.categoryId = categoryId;
  }

}
