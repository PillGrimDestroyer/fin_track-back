package kz.hawk.fintrack.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Permission {
  ADMIN("admin:all");

  private final String permission;
}
