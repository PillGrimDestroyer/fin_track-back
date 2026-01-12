package kz.hawk.fintrack.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public enum Role {
  USER(Set.of()),
  ADMIN(Set.of(Permission.ADMIN));

  private final Set<Permission> permissions;

  public Set<SimpleGrantedAuthority> getAuthorities() {
    return getPermissions().stream()
                           .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                           .collect(Collectors.toSet());
  }
}
