package kz.hawk.risesecurity.model.request;

import lombok.Data;

@Data
public class AuthenticationRequest {
  private String email;
  private String password;
}
