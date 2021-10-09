package kz.hawk.risesecurity.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Permission {
  ADMIN("admin:all");

  private final String permission;
}
