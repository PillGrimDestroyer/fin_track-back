package kz.hawk.fintrack.beans;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class OkHttpClientFactory {

  @Bean
  public OkHttpClient okHttpClient() {
    return new OkHttpClient.Builder()
      .readTimeout(15, TimeUnit.SECONDS)
      .retryOnConnectionFailure(true)
      .connectionPool(new ConnectionPool(200, 5L, TimeUnit.MINUTES))
      .build();
  }

}
