package kz.hawk.fintrack.util;


import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class Json {

  public static ObjectMapper getFriendlyObjectMapper() {
    return new ObjectMapper();
  }

  public static <T> String toJson(T value) {
    return getFriendlyObjectMapper().writeValueAsString(value);
  }

  public static <T> String toPrettyJson(T value) {
    return getFriendlyObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(value);
  }

  public static <T> T toObject(String json, Class<T> clazz) {
    return getFriendlyObjectMapper().readValue(json, clazz);
  }

  public static <T> T toObject(String json, TypeReference<T> clazz) {
    return getFriendlyObjectMapper().readValue(json, clazz);
  }

  public static <T> TypeReference<T> toTypeReference(T obj) {
    return new TypeReference<>() {
    };
  }

}
