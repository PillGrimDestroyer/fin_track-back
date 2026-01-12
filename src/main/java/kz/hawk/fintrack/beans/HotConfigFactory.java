package kz.hawk.fintrack.beans;

import kz.greetgo.conf.hot.FileConfigFactory;
import kz.hawk.fintrack.config.DbConfig;
import kz.hawk.fintrack.config.JwtConfig;
import kz.hawk.fintrack.config.RequestDefenderConfig;
import kz.hawk.fintrack.util.App;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class HotConfigFactory extends FileConfigFactory {

  @Override
  protected Path getBaseDir() {
    return App.appDir().resolve("config");
  }

  @Bean
  public DbConfig dbConfig() {
    return createConfig(DbConfig.class);
  }

  @Bean
  public JwtConfig jwtConfig() {
    return createConfig(JwtConfig.class);
  }

  @Bean
  public RequestDefenderConfig requestDefenderConfig() {
    return createConfig(RequestDefenderConfig.class);
  }

}
