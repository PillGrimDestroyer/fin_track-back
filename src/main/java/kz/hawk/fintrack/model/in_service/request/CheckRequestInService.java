package kz.hawk.fintrack.model.in_service.request;

import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class CheckRequestInService {
  private UUID                secretId;
  private String              header;
  private Map<String, Object> body;
}
