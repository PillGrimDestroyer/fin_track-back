package kz.hawk.fintrack.model.dao;


import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * @author megam
 * @since 13.01.2026 14:45
 */
@Data
public class CategoryDto {
  private UUID    id;
  private String  nameRu;
  private String  nameEn;
  private String  icon;
  private Date    createdAt;
  private UserDto user;

  public CategoryDto() {
  }

  public CategoryDto(String nameRu, String nameEn, String icon, UserDto user) {
    this.nameRu = nameRu;
    this.nameEn = nameEn;
    this.icon = icon;
    this.user = user;
  }
}
