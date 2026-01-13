package kz.hawk.fintrack.register;

import jakarta.servlet.http.HttpServletRequest;
import kz.hawk.fintrack.model.dao.UserDto;
import kz.hawk.fintrack.model.request.AuthenticationRequest;
import kz.hawk.fintrack.model.request.RegisterRequest;
import kz.hawk.fintrack.model.response.AuthenticationResponse;
import org.springframework.security.core.AuthenticationException;


public interface AuthorizeRegister {
  AuthenticationResponse authenticate(AuthenticationRequest request) throws AuthenticationException;

  AuthenticationResponse refreshToken(HttpServletRequest request) throws AuthenticationException;

  AuthenticationResponse refreshToken(String email) throws AuthenticationException;

  boolean checkEmailExists(String email);

  UserDto register(RegisterRequest request);

  AuthenticationResponse registerThenAuthenticate(RegisterRequest request);
}
