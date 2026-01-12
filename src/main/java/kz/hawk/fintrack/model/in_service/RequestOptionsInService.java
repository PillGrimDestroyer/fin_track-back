package kz.hawk.fintrack.model.in_service;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestOptionsInService {

  private String host;
  private int    port;

}
