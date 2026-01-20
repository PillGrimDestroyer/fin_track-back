package kz.hawk.fintrack.model.dao;


import kz.hawk.fintrack.model.enums.Transaction;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author megam
 * @since 18.01.2026 7:30
 */
@Data
public class TransactionDto {
  private UUID          id;
  private BigDecimal    amount;
  private Transaction   type;
  private String        description;
  private LocalDateTime transactionDate;
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private UserDto       user;
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private CategoryDto   category;
  private LocalDateTime updatedAt;
  private LocalDateTime createdAt;
}
