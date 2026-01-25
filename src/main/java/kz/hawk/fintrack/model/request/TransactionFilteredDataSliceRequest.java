package kz.hawk.fintrack.model.request;


import kz.hawk.fintrack.model.enums.Transaction;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;

/**
 * @author megam
 * @since 25.01.2026 10:12
 */
@Data
@Builder
public class TransactionFilteredDataSliceRequest {

  private Transaction transactionType;

  private String description;

  @Positive(message = "'maxAmount' must be a positive number (greater than zero)")
  private BigDecimal maxAmount;

  @Positive(message = "'minAmount' must be a positive number (greater than zero)")
  private BigDecimal minAmount;

  private Map.Entry<OffsetDateTime, OffsetDateTime> transactionRange;

  private String categoryNameEn;

  @Min(value = 1, message = "'limit' must be greater than 0")
  private int limit;

  @Positive(message = "'offset' must be a positive number (greater than zero)")
  private int offset;

}
