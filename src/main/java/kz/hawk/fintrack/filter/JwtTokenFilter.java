package kz.hawk.fintrack.filter;

import kz.hawk.fintrack.beans.JwtTokenProvider;
import kz.hawk.fintrack.exception.JwtAuthenticationException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

  private final JwtTokenProvider jwtTokenProvider;

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
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }

      filterChain.doFilter(request, response);
    } catch (JwtAuthenticationException e) {
      SecurityContextHolder.clearContext();
      response.sendError(e.getHttpStatus().value());

      logger.error(e.getMessage());

      try {
        filterChain.doFilter(request, response);
      } catch (Exception ignore) {
        // ignore
      }
    }
  }

}
