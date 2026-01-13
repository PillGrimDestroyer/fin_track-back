package kz.hawk.fintrack.config;

import kz.greetgo.conf.hot.DefaultLongValue;
import kz.greetgo.conf.hot.DefaultStrValue;
import kz.greetgo.conf.hot.Description;
import kz.greetgo.conf.hot.FirstReadEnv;

@Description("Настройки для управления jwt")
public interface JwtConfig {

  @Description("Секретный ключ для шифрования токена")
  @DefaultStrValue("The JWT JWA Specification (RFC 7518, Section 3.2) states that keys used with HMAC-SHA algorithms MUST have a size >= 256 bits (the key size must be greater than or equal to the hash output size)")
  @FirstReadEnv("JWT_SECRET_KEY")
  String secretKey();

  @Description("Заголовок в который устанавливается токен")
  @DefaultStrValue("Authorization")
  @FirstReadEnv("JWT_HEADER")
  String header();

  @Description("Продолжительность валидности токена (в секундах)")
  @DefaultLongValue(1800) // 30 минут
  @FirstReadEnv("JWT_VALID_TIME")
  long validTime();

  @Description("Продолжительность валидности рефреш токена (в секундах)")
  @DefaultLongValue(604800) // 7 дней
  @FirstReadEnv("JWT_REFRESH_VALID_TIME")
  long refreshValidTime();

}
