package kz.hawk.fintrack.register;


import kz.hawk.fintrack.exception.JwtAuthenticationException;
import org.springframework.security.core.Authentication;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author megam
 * @since 19.01.2026 16:34
 */
public interface SessionRegister {

  void create(@NotNull Authentication authentication);

  UUID currentUserId() throws JwtAuthenticationException;

}
