package kz.hawk.risesecurity.impl;

import kz.hawk.risesecurity.dao.UserDao;
import kz.hawk.risesecurity.model.dao.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserDao userDao;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserDto userDto = Optional.ofNullable(userDao.getByEmail(email))
                              .orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));

    return userDto.toUserDetails();
  }
}
