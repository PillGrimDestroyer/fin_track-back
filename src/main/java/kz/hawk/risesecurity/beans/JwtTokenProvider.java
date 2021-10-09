package kz.hawk.risesecurity.beans;


import io.jsonwebtoken.*;
import kz.hawk.risesecurity.config.JwtConfig;
import kz.hawk.risesecurity.exception.JwtAuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
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

  public String createToken(String username, String role) {
    Claims claims = Jwts.claims().setSubject(username);
    claims.put("role", role);
    Date now      = new Date();
    Date validity = new Date(now.getTime() + jwtConfig.validTime() * 1000);

    return Jwts.builder()
               .setClaims(claims)
               .setIssuedAt(now)
               .setExpiration(validity)
               .signWith(SignatureAlgorithm.HS256, encodedSecretKey)
               .compact();
  }

  public boolean validateToken(String token) {
    try {
      Jws<Claims> claimsJws = Jwts.parser().setSigningKey(encodedSecretKey).parseClaimsJws(token);
      return !claimsJws.getBody().getExpiration().before(new Date());
    } catch (JwtException | IllegalArgumentException e) {
      throw new JwtAuthenticationException("JWT token is expired or invalid", HttpStatus.UNAUTHORIZED);
    }
  }

  public Authentication getAuthentication(String token) {
    UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  public String getUsername(String token) {
    return Jwts.parser().setSigningKey(encodedSecretKey).parseClaimsJws(token).getBody().getSubject();
  }

  public String resolveToken(HttpServletRequest request) {
    return request.getHeader(jwtConfig.header());
  }

}
