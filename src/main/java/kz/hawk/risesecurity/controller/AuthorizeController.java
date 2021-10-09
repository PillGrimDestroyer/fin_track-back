package kz.hawk.risesecurity.controller;

import kz.hawk.risesecurity.beans.JwtTokenProvider;
import kz.hawk.risesecurity.dao.UserDao;
import kz.hawk.risesecurity.model.request.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/authorize")
@RequiredArgsConstructor
public class AuthorizeController {

  private final AuthenticationManager authenticationManager;
  private final UserDao               userDao;
  private final JwtTokenProvider      jwtTokenProvider;

  @PostMapping("/login")
  public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        request.getEmail(),
        request.getPassword()
      ));

      var user = Optional.ofNullable(userDao.getByEmail(request.getEmail()))
                         .orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));

      var token    = jwtTokenProvider.createToken(request.getEmail(), user.getRole().name());
      var response = new HashMap<>();

      response.put("email", request.getEmail());
      response.put("token", token);

      return ResponseEntity.ok(response);
    } catch (AuthenticationException e) {
      return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
    }
  }

  @PostMapping("/logout")
  public void logout(HttpServletRequest request, HttpServletResponse response) {
    var securityContextLogoutHandler = new SecurityContextLogoutHandler();
    securityContextLogoutHandler.logout(request, response, null);
  }

}
