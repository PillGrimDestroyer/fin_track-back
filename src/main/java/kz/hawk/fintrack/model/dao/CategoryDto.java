package kz.hawk.fintrack.model.dao;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * @author megam
 * @since 13.01.2026 14:45
 */
@Data
@NoArgsConstructor
public class CategoryDto {
  private UUID           id;
  private String         nameRu;
  private String         nameEn;
  private String         icon;
  private OffsetDateTime createdAt;
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private UserDto        user;

  public CategoryDto(String nameRu, String nameEn, String icon, UserDto user) {
    this.nameRu = nameRu;
    this.nameEn = nameEn;
    this.icon = icon;
    this.user = user;
  }
}
