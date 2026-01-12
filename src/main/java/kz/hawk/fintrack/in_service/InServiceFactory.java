package kz.hawk.fintrack.in_service;

import kz.hawk.fintrack.config.RequestDefenderConfig;
import kz.hawk.fintrack.in_service.impl.RequestDefenderInServiceFake;
import kz.hawk.fintrack.in_service.impl.RequestDefenderInServiceReal;
import kz.hawk.fintrack.model.in_service.RequestOptionsInService;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.IdGenerator;

@Component
@RequiredArgsConstructor
public class InServiceFactory {

  private final OkHttpClient          okHttpClient;
  private final IdGenerator           idGenerator;
  private final RequestDefenderConfig requestDefenderConfig;

  @Bean
  public RequestDefenderInService requestDefenderInService() {
    if (requestDefenderConfig.useFake()) {
      return new RequestDefenderInServiceFake(idGenerator);
    }

    var options = RequestOptionsInService.builder()
                                         .host(requestDefenderConfig.host())
                                         .port(requestDefenderConfig.port())
                                         .build();

    return new RequestDefenderInServiceReal(okHttpClient, options);
  }

}
