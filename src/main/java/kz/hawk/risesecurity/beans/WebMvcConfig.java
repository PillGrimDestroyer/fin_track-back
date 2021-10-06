package kz.hawk.risesecurity.beans;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedOriginPatterns("*")
            .allowedHeaders("*")
            .exposedHeaders("Content-Disposition")
            .allowCredentials(true)
            .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE");
  }

}
