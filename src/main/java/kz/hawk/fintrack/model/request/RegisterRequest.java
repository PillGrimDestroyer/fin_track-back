package kz.hawk.fintrack.model.request;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author megam
 * @since 13.01.2026 13:04
 */
@Data
public class RegisterRequest {
  @NotEmpty(message = "'email' is required")
  @Email(message = "'email' is not valid")
  private String email;

  @NotEmpty(message = "'password' is required")
  private String password;

  @NotEmpty(message = "'firstName' is required")
  private String firstName;

  @NotEmpty(message = "'lastName' is required")
  private String lastName;
}
