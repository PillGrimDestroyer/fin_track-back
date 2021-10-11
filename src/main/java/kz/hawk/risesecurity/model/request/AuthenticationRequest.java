package kz.hawk.risesecurity.model.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AuthenticationRequest {
  @NotEmpty(message = "'email' is required")
  private String email;

  @NotEmpty(message = "'password' is required")
  private String password;
}
