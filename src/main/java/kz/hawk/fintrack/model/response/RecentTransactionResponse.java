package kz.hawk.fintrack.model.response;


import kz.hawk.fintrack.model.dao.TransactionDto;
import kz.hawk.fintrack.model.enums.Transaction;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author megam
 * @since 20.01.2026 12:30
 */
@Data
public class RecentTransactionResponse {
  private UUID             id;
  private BigDecimal       amount;
  private Transaction      type;
  private String           description;
  private LocalDateTime    transactionDate;
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private CategoryResponse category;
  private LocalDateTime    updatedAt;
  private LocalDateTime    createdAt;

  public static RecentTransactionResponse fromDto(TransactionDto dto) {
    RecentTransactionResponse res = new RecentTransactionResponse();

    res.setId(dto.getId());
    res.setAmount(dto.getAmount());
    res.setType(dto.getType());
    res.setDescription(dto.getDescription());
    res.setTransactionDate(dto.getTransactionDate());
    res.setCategory(CategoryResponse.fromDto(dto.getCategory()));
    res.setUpdatedAt(dto.getUpdatedAt());
    res.setCreatedAt(dto.getCreatedAt());

    return res;
  }
}
