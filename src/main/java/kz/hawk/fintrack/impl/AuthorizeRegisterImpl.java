package kz.hawk.fintrack.impl;

import kz.hawk.fintrack.beans.JwtTokenProvider;
import kz.hawk.fintrack.dao.UserDao;
import kz.hawk.fintrack.exception.JwtAuthenticationException;
import kz.hawk.fintrack.model.dao.UserDto;
import kz.hawk.fintrack.model.enums.Role;
import kz.hawk.fintrack.model.request.AuthenticationRequest;
import kz.hawk.fintrack.model.request.RegisterRequest;
import kz.hawk.fintrack.model.response.AuthenticationResponse;
import kz.hawk.fintrack.register.AuthorizeRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorizeRegisterImpl implements AuthorizeRegister {

  private final AuthenticationManager authenticationManager;
  private final UserDao               userDao;
  private final JwtTokenProvider      jwtTokenProvider;
  private final PasswordEncoder       passwordEncoder;

  @Override
  public AuthenticationResponse authenticate(AuthenticationRequest request) throws AuthenticationException {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
      request.getEmail(),
      request.getPassword()
    ));

    return refreshToken(request.getEmail());
  }

  @Override
  public AuthenticationResponse refreshToken(HttpServletRequest request) throws AuthenticationException {
    var token = jwtTokenProvider.resolveToken(request);

    if (token == null) {
      throw new JwtAuthenticationException("Must provide JWT refresh token", HttpStatus.FORBIDDEN);
    }

    var email = jwtTokenProvider.getUsername(token);

    return refreshToken(email);
  }

  @Override
  public AuthenticationResponse refreshToken(String email) throws AuthenticationException {
    var user = Optional.ofNullable(userDao.getByEmail(email))
                       .orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));

    var refreshToken = jwtTokenProvider.createRefreshToken(email);
    var token        = jwtTokenProvider.createToken(email, user.getRole().name());

    var resp = new AuthenticationResponse();

    resp.setEmail(email);
    resp.setToken(token);
    resp.setRefreshToken(refreshToken);

    return resp;
  }

  @Override
  public boolean checkEmailExists(String email) {
    return userDao.checkEmailExists(email);
  }

  @Override
  public UserDto register(RegisterRequest request) {
    var role = Role.USER;

    userDao.createUser(
      request.getEmail(),
      passwordEncoder.encode(request.getPassword()),
      request.getFirstName(),
      request.getLastName(),
      role
    );

    return userDao.getByEmail(request.getEmail());
  }

  @Override
  public AuthenticationResponse registerThenAuthenticate(RegisterRequest request) {
    register(request);

    var authenticationRequest = new AuthenticationRequest();

    authenticationRequest.setEmail(request.getEmail());
    authenticationRequest.setPassword(request.getPassword());

    return authenticate(authenticationRequest);
  }

}
