package kz.hawk.risesecurity;

import kz.hawk.risesecurity.beans.ApplicationFinisher;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PreDestroy;

@SpringBootApplication
@RequiredArgsConstructor
public class RiseSecurityApplication {

  private final ApplicationFinisher applicationFinisher;

  public static void main(String[] args) {
    SpringApplication.run(RiseSecurityApplication.class, args);
  }

  @PreDestroy
  public void preDestroy() {
    applicationFinisher.finishApplication();
  }
}
