package kz.hawk.risesecurity.config;

import kz.greetgo.conf.hot.*;

@Description("Конфиг по работе с микросервисом 'request-defender'")
public interface RequestDefenderConfig {

  @Description("Использовать ли фейк реализацию")
  @DefaultBoolValue(true)
  @FirstReadEnv("REQUEST_DEFENDER_FAKE")
  boolean useFake();

  @Description("Аддресс микросервиса")
  @DefaultStrValue("localhost")
  @FirstReadEnv("REQUEST_DEFENDER_HOST")
  String host();

  @Description("Порт микросервиса")
  @DefaultIntValue(9090)
  @FirstReadEnv("REQUEST_DEFENDER_PORT")
  int port();

}
