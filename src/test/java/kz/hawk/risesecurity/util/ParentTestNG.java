package kz.hawk.risesecurity.util;

import kz.hawk.risesecurity.dao.UserDaoTest;
import kz.hawk.risesecurity.model.dao.UserDto;
import kz.hawk.risesecurity.model.enums.Role;
import kz.hawk.risesecurity.model.enums.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.IdGenerator;
import org.testng.annotations.BeforeMethod;


@SpringBootTest
@ContextConfiguration(classes = {BeanConfigTests.class})
public class ParentTestNG extends AbstractTestNGSpringContextTests {

  @Autowired
  private IdGenerator idGenerator;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserDaoTest userDaoTest;

  @BeforeMethod
  public void beforeRunTest() {

  }

  @Test
  public void generateUUID() {
    System.out.println(idGenerator.generateId());
  }

  @Test
  public void createUser() {
    UserDto user = new UserDto();

    user.setId(idGenerator.generateId());
    user.setEmail("test@test.test");
    user.setRole(Role.USER);
    user.setPassword(passwordEncoder.encode("test"));
    user.setStatus(Status.ACTIVE);
    user.setFirstName("test");
    user.setLastName("testov");

    userDaoTest.saveUser(user);
  }

}
