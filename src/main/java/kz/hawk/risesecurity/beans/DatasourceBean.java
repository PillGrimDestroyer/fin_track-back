package kz.hawk.risesecurity.beans;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import kz.hawk.risesecurity.config.DbConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@RequiredArgsConstructor
public class DatasourceBean {

  private final DbConfig dbConfig;

  @Bean
  public DataSource dataSource() {
    var url = "jdbc:postgresql://" + dbConfig.host() + ":" + dbConfig.port() + "/" + dbConfig.dbName();

    var config = new HikariConfig();

    config.setDriverClassName("org.postgresql.Driver");
    config.setJdbcUrl(url);
    config.setUsername(dbConfig.username());
    config.setPassword(dbConfig.password());
    config.setMaximumPoolSize(50);

    return new HikariDataSource(config);
  }

}
