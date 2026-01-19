package kz.hawk.fintrack.model.response;


import kz.hawk.fintrack.model.dao.CategoryDto;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * @author megam
 * @since 19.01.2026 16:26
 */
@Data
public class CategoryResponse {

  private UUID   id;
  private String nameRu;
  private String nameEn;
  private String icon;
  private Date   createdAt;


  public static CategoryResponse fromDto(CategoryDto categoryDto) {
    CategoryResponse response = new CategoryResponse();
    response.id = categoryDto.getId();
    response.nameRu = categoryDto.getNameRu();
    response.nameEn = categoryDto.getNameEn();
    response.icon = categoryDto.getIcon();
    response.createdAt = categoryDto.getCreatedAt();
    return response;
  }

}
