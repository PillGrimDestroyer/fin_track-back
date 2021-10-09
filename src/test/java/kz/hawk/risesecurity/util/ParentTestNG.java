package kz.hawk.risesecurity.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.IdGenerator;
import org.testng.annotations.BeforeMethod;


@SpringBootTest
@ContextConfiguration(classes = {BeanConfigTests.class})
public class ParentTestNG extends AbstractTestNGSpringContextTests {

  @Autowired
  private IdGenerator idGenerator;

  @BeforeMethod
  public void beforeRunTest() {

  }

  @Test
  public void generateUUID() {
    System.out.println(idGenerator.generateId());
  }

}
