package kz.hawk.fintrack.util;

import kz.hawk.fintrack.dao.UserDao;
import kz.hawk.fintrack.dao.UserDaoTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.IdGenerator;
import org.testng.annotations.BeforeMethod;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ContextConfiguration(classes = {BeanConfigTests.class})
public class ParentTestNG extends AbstractTestNGSpringContextTests {

  @Autowired
  private IdGenerator idGenerator;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserDaoTest userDaoTest;

  @Autowired
  private UserDao userDao;

  @BeforeMethod
  public void beforeRunTest() {

  }

  @Test
  public void generateUUID() {
    System.out.println(idGenerator.generateId());
  }

  @Test
  public void createUser() {
    String email = "test@test.test";

    userDaoTest.saveUser(
        email,
        passwordEncoder.encode("test"),
        "test",
        "testov"
    );

    //
    //
    var realUser = userDao.getByEmail(email);
    //
    //

    System.out.println();
    System.out.print("Real user: ");
    System.out.println(realUser);
    System.out.println();

    assertThat(realUser).isNotNull();
  }

}
