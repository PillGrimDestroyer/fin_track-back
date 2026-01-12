package kz.hawk.fintrack.model.response;

import lombok.Data;

@Data
public class AuthenticationResponse {
  private String email;
  private String token;
  private String refreshToken;
}
