package kz.hawk.risesecurity.filter;

import kz.hawk.risesecurity.beans.JwtTokenProvider;
import kz.hawk.risesecurity.exception.JwtAuthenticationException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {

  private final JwtTokenProvider jwtTokenProvider;

  @SneakyThrows
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
    String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);

    try {
      if (token != null && jwtTokenProvider.validateToken(token)) {
        Authentication authentication = jwtTokenProvider.getAuthentication(token);

        if (authentication != null) {
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }
    } catch (JwtAuthenticationException e) {
      SecurityContextHolder.clearContext();
      ((HttpServletResponse) servletResponse).sendError(e.getHttpStatus().value());

      logger.error(e.getMessage(), e);
    }

    filterChain.doFilter(servletRequest, servletResponse);
  }

}
