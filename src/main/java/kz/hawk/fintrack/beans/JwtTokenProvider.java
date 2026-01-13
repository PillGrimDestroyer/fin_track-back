package kz.hawk.fintrack.beans;


import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import kz.hawk.fintrack.config.JwtConfig;
import kz.hawk.fintrack.exception.JwtAuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

  private final UserDetailsService userDetailsService;
  private final JwtConfig          jwtConfig;

  private String encodedSecretKey;

  @PostConstruct
  protected void init() {
    encodedSecretKey = Base64.getEncoder().encodeToString(jwtConfig.secretKey().getBytes());
  }

  public String createToken(String username, @Nullable String role) {
    return createToken(username, role, jwtConfig.validTime());
  }

  public String createRefreshToken(String username) {
    return createToken(username, null, jwtConfig.refreshValidTime());
  }

  public boolean validateToken(String token) {
    try {
      Jws<Claims> claimsJws = Jwts.parser().setSigningKey(encodedSecretKey).build().parseClaimsJws(token);
      return claimsJws.getBody().getExpiration().after(new Date());
    } catch (JwtException | IllegalArgumentException e) {
      throw new JwtAuthenticationException("JWT token is expired or invalid", HttpStatus.UNAUTHORIZED);
    }
  }

  public Authentication getAuthentication(String token) {
    UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  public String getUsername(String token) {
    return Jwts.parser().setSigningKey(encodedSecretKey).build().parseClaimsJws(token).getBody().getSubject();
  }

  public String resolveToken(HttpServletRequest request) {
    return request.getHeader(jwtConfig.header());
  }

  private String createToken(String username, @Nullable String role, long validTime) {
    var claims = Jwts.claims().subject(username);

    if (role != null) {
      claims.add("role", role);
    }

    var now      = new Date();
    var validity = new Date(now.getTime() + validTime * 1000);

    return Jwts.builder()
               .claims(claims.build())
               .issuedAt(now)
               .expiration(validity)
               .signWith(SignatureAlgorithm.HS256, encodedSecretKey)
               .compact();
  }

}
