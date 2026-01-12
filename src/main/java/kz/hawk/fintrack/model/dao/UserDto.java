package kz.hawk.fintrack.model.dao;

import kz.hawk.fintrack.model.enums.Role;
import kz.hawk.fintrack.model.enums.Status;
import lombok.Data;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.UUID;

@Data
public class UserDto {
  private UUID   id;
  private String email;
  private String password;
  private String firstName;
  private String lastName;
  private Role   role;
  private Status status;
  private Date   registeredAt;


  public UserDetails toUserDetails() {
    return new User(
      email,
      password,
      Status.ACTIVE.equals(status),
      Status.ACTIVE.equals(status),
      Status.ACTIVE.equals(status),
      Status.ACTIVE.equals(status),
      role.getAuthorities()
    );
  }
}
