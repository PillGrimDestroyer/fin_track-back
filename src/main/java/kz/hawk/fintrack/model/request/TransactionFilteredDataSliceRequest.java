package kz.hawk.fintrack.model.request;


import kz.hawk.fintrack.model.enums.Transaction;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * @author megam
 * @since 25.01.2026 10:12
 */
@Data
public class TransactionFilteredDataSliceRequest {

  private Transaction transactionType;

  private String description;

  @Positive(message = "'maxAmount' must be a positive number (greater than zero)")
  private BigDecimal maxAmount;

  @Positive(message = "'minAmount' must be a positive number (greater than zero)")
  private BigDecimal minAmount;

  private OffsetDateTime transactionRangeFrom;

  private OffsetDateTime transactionRangeTo;

  private String categoryNameEn;

  @Min(value = 1, message = "'limit' must be greater than 0")
  private int limit;

  @Positive(message = "'offset' must be a positive number (greater than zero)")
  private int offset;

}
