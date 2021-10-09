package kz.hawk.risesecurity.config;

import kz.greetgo.conf.hot.DefaultLongValue;
import kz.greetgo.conf.hot.DefaultStrValue;
import kz.greetgo.conf.hot.Description;
import kz.greetgo.conf.hot.FirstReadEnv;

@Description("Настройки для управления jwt")
public interface JwtConfig {

  @Description("Секретный ключ для шифрования токена")
  @DefaultStrValue("test")
  @FirstReadEnv("JWT_SECRET_KEY")
  String secretKey();

  @Description("Заголовок в который устанавливается токен")
  @DefaultStrValue("Authorization")
  @FirstReadEnv("JWT_HEADER")
  String header();

  @Description("Продолжительность валидности токена (в секундах)")
  @DefaultLongValue(604800) // 7 дней
  @FirstReadEnv("JWT_VALID_TIME")
  long validTime();

}
