package kz.hawk.fintrack;

import kz.hawk.fintrack.beans.ApplicationFinisher;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PreDestroy;

@SpringBootApplication
@RequiredArgsConstructor
public class FinTrackApplication {

  private final ApplicationFinisher applicationFinisher;

  public static void main(String[] args) {
    SpringApplication.run(FinTrackApplication.class, args);
  }

  @PreDestroy
  public void preDestroy() {
    applicationFinisher.finishApplication();
  }
}
