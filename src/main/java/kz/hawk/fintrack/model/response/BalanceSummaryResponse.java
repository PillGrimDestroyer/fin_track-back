package kz.hawk.fintrack.model.response;


import lombok.Data;

import java.math.BigDecimal;

/**
 * @author megam
 * @since 20.01.2026 15:29
 */
@Data
public class BalanceSummaryResponse {
  private BigDecimal total;
  private BigDecimal income;
  private BigDecimal expenses;
}
