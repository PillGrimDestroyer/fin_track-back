package kz.hawk.risesecurity.register;

import kz.hawk.risesecurity.model.request.AuthenticationRequest;
import kz.hawk.risesecurity.model.response.AuthenticationResponse;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;

public interface AuthorizeRegister {
  AuthenticationResponse authenticate(AuthenticationRequest request) throws AuthenticationException;

  AuthenticationResponse refreshToken(HttpServletRequest request) throws AuthenticationException;

  AuthenticationResponse refreshToken(String email) throws AuthenticationException;
}
