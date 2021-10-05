package kz.hawk.risesecurity.util;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;


@SpringBootTest
@ContextConfiguration(classes = {BeanConfigTests.class})
public class ParentTestNG extends AbstractTestNGSpringContextTests {

  @BeforeMethod
  public void beforeRunTest() {

  }

}
