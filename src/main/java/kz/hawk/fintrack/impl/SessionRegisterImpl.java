package kz.hawk.fintrack.impl;


import kz.hawk.fintrack.exception.JwtAuthenticationException;
import kz.hawk.fintrack.model.dao.UserDto;
import kz.hawk.fintrack.register.SessionRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

/**
 * @author megam
 * @since 19.01.2026 16:35
 */
@Component
@RequiredArgsConstructor
public class SessionRegisterImpl implements SessionRegister {

  @Override
  public void create(@NotNull Authentication authentication) {
    SecurityContext context = SecurityContextHolder.createEmptyContext();
    context.setAuthentication(authentication);
    SecurityContextHolder.setContext(context);
  }

  @Override
  public UUID currentUserId() throws JwtAuthenticationException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    return Optional.ofNullable(authentication)
                   .map(Authentication::getPrincipal)
                   .filter(UserDto.UserDetails.class::isInstance)
                   .map(UserDto.UserDetails.class::cast)
                   .map(UserDto.UserDetails::getId)
                   .orElseThrow(() -> new JwtAuthenticationException("JWT token is invalid", HttpStatus.UNAUTHORIZED));
  }

}
