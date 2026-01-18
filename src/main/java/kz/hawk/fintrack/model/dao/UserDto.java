package kz.hawk.fintrack.model.dao;

import kz.hawk.fintrack.model.enums.Role;
import kz.hawk.fintrack.model.enums.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Data
public class UserDto {
  private UUID   id;
  private String email;
  private String passwordHash;
  private String firstName;
  private String lastName;
  private Role   role;
  private Status status;
  private Date   registeredAt;


  public UserDetails toUserDetails() {
    return new UserDetails(
      id,
      email,
      passwordHash,
      Status.ACTIVE.equals(status),
      Status.ACTIVE.equals(status),
      Status.ACTIVE.equals(status),
      Status.ACTIVE.equals(status),
      role.getAuthorities()
    );
  }

  @Data
  @EqualsAndHashCode(callSuper = true)
  public static class UserDetails extends User {
    private UUID id;

    public UserDetails(UUID id, String username, @Nullable String password,
                       Collection<? extends GrantedAuthority> authorities) {
      super(username, password, authorities);
      this.id = id;
    }

    public UserDetails(UUID id, String username, @Nullable String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
                       boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
      super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
      this.id = id;
    }

  }
}
