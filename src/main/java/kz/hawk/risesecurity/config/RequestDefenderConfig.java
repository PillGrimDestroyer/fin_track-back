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

  @Description("Заголовок в котором хранится зашифрованное тело запроса")
  @DefaultStrValue("Security")
  @FirstReadEnv("REQUEST_DEFENDER_SECURITY_HEADER")
  String securityHeader();

  @Description("Заголовк в котором хранится токен запроса, полученые от 'request-defender'")
  @DefaultStrValue("RequestToken")
  @FirstReadEnv("REQUEST_DEFENDER_TOKEN_HEADER")
  String requestTokenHeader();

}
