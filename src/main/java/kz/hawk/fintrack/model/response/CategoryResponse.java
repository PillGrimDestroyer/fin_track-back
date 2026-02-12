package kz.hawk.fintrack.model.response;


import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * @author megam
 * @since 19.01.2026 16:26
 */
@Data
public class CategoryResponse {

  private UUID           id;
  private String         nameRu;
  private String         nameEn;
  private String         icon;
  private Boolean        isDefault;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;

}
