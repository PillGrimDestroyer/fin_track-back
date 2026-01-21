package kz.hawk.fintrack.model.response;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author megam
 * @since 21.01.2026 13:27
 */
@Data
public class WeeklySpendingResponse {
  private Date       date;
  private BigDecimal amount;
}
