package kz.hawk.fintrack.model.response;


import lombok.Data;

import java.math.BigDecimal;

/**
 * @author megam
 * @since 20.01.2026 13:28
 */
@Data
public class SpendByCategoryResponse {
  private String     categoryNameRu;
  private String     categoryNameEn;
  private BigDecimal totalAmount;
}
