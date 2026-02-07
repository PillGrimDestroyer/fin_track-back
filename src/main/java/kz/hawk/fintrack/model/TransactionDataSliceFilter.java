package kz.hawk.fintrack.model;


import kz.hawk.fintrack.model.enums.Transaction;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * @author Work
 * @since 07.02.2026 9:29
 */
@Data
@NoArgsConstructor
@FieldNameConstants
public class TransactionDataSliceFilter {

  private @NotNull  UUID           userId;
  private @Nullable Transaction    type;
  private @Nullable String         description;
  private @Nullable BigDecimal     maxAmount;
  private @Nullable BigDecimal     minAmount;
  private @Nullable OffsetDateTime rangeFrom;
  private @Nullable OffsetDateTime rangeTo;
  private @Nullable String         category;
  private           int            limit;
  private           int            page;

}
