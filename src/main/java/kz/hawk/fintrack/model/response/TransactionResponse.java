package kz.hawk.fintrack.model.response;


import kz.hawk.fintrack.model.enums.Transaction;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * @author megam
 * @since 24.01.2026 15:51
 */
@Data
public class TransactionResponse {

  private UUID             id;
  private BigDecimal       amount;
  private Transaction      type;
  private String           description;
  private OffsetDateTime   transactionDate;
  private CategoryResponse category;
  private OffsetDateTime   updatedAt;
  private OffsetDateTime   createdAt;

}
