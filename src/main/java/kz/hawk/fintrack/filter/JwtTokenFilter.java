package kz.hawk.fintrack.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.hawk.fintrack.beans.JwtTokenProvider;
import kz.hawk.fintrack.exception.JwtAuthenticationException;
import kz.hawk.fintrack.register.SessionRegister;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Arrays;
import java.util.function.Predicate;

import static kz.hawk.fintrack.beans.SecurityConfig.FREE_ACCESS_URLS;


@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

  private final JwtTokenProvider jwtTokenProvider;
  private final SessionRegister  sessionRegister;

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    String path = request.getRequestURI();

    return Arrays.stream(FREE_ACCESS_URLS).anyMatch(Predicate.isEqual(path));
  }

  @SneakyThrows
  @Override
  protected void doFilterInternal(
    @NotNull HttpServletRequest request,
    @NotNull HttpServletResponse response,
    @NotNull FilterChain filterChain
  ) {
    String token = jwtTokenProvider.resolveToken(request);

    try {
      if (token != null && jwtTokenProvider.validateToken(token)) {
        Authentication authentication = jwtTokenProvider.getAuthentication(token);

        if (authentication != null) {
          sessionRegister.create(authentication);
        }
      }

      filterChain.doFilter(request, response);
    } catch (JwtAuthenticationException e) {
      SecurityContextHolder.clearContext();
      response.sendError(e.getHttpStatus().value(), e.getMessage());

      logger.error(e.getMessage());
    }
  }

}
