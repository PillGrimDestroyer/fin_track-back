package kz.hawk.fintrack.controller;

import jakarta.servlet.http.HttpServletRequest;
import kz.hawk.fintrack.model.request.AuthenticationRequest;
import kz.hawk.fintrack.model.request.RegisterRequest;
import kz.hawk.fintrack.model.response.AuthenticationResponse;
import kz.hawk.fintrack.register.AuthorizeRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
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

  @GetMapping("/refresh")
  public ResponseEntity<?> refreshToken(HttpServletRequest request) {
    try {
      return ResponseEntity.ok(authorizeRegister.refreshToken(request));
    } catch (AuthenticationException e) {
      var msg = Optional.ofNullable(e.getMessage()).orElse("Invalid email/password combination");
      return new ResponseEntity<>(msg, HttpStatus.FORBIDDEN);
    }
  }

  @GetMapping("/check-email-exists")
  //todo Must return response model
  public boolean checkEmailExists(@RequestParam @NotEmpty String email) {
    return authorizeRegister.checkEmailExists(email);
  }

  @PostMapping("/register-then-authenticate")
  public AuthenticationResponse register(@Valid @RequestBody RegisterRequest request) {
    return authorizeRegister.registerThenAuthenticate(request);
  }

}
