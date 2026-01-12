package kz.hawk.fintrack.model.in_service.response;

import lombok.Data;

@Data
public class CheckResponseInService {
  private boolean isValid;
  private String  errorMsg;
}
