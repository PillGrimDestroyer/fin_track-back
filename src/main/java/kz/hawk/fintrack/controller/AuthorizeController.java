package kz.hawk.fintrack.controller;

import kz.hawk.fintrack.model.request.AuthenticationRequest;
import kz.hawk.fintrack.register.AuthorizeRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/authorize")
@RequiredArgsConstructor
public class AuthorizeController {

  private final AuthorizeRegister authorizeRegister;

  @PostMapping("/login")
  public ResponseEntity<?> authenticate(@Valid @RequestBody AuthenticationRequest request) {
    try {
      return ResponseEntity.ok(authorizeRegister.authenticate(request));
    } catch (AuthenticationException e) {
      var msg = Optional.ofNullable(e.getMessage()).orElse("Invalid email/password combination");
      return new ResponseEntity<>(msg, HttpStatus.FORBIDDEN);
    }
  }

  @PostMapping("/refresh")
  public ResponseEntity<?> refreshToken(HttpServletRequest request) {
    try {
      return ResponseEntity.ok(authorizeRegister.refreshToken(request));
    } catch (AuthenticationException e) {
      var msg = Optional.ofNullable(e.getMessage()).orElse("Invalid email/password combination");
      return new ResponseEntity<>(msg, HttpStatus.FORBIDDEN);
    }
  }

}
