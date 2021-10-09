package kz.hawk.risesecurity.beans;

import kz.greetgo.conf.hot.FileConfigFactory;
import kz.hawk.risesecurity.config.DbConfig;
import kz.hawk.risesecurity.config.JwtConfig;
import kz.hawk.risesecurity.util.App;
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

}
