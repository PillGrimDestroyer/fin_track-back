package kz.hawk.fintrack.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {

  public static ObjectMapper getFriendlyObjectMapper() {
    var mapper = new ObjectMapper();

    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    return mapper;
  }

  public static <T> String toJson(T value) throws JsonProcessingException {
    return getFriendlyObjectMapper().writeValueAsString(value);
  }

  public static <T> String toPrettyJson(T value) throws JsonProcessingException {
    return getFriendlyObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(value);
  }

  public static <T> T toObject(String json, Class<T> clazz) throws JsonProcessingException {
    return getFriendlyObjectMapper().readValue(json, clazz);
  }

  public static <T> T toObject(String json, TypeReference<T> clazz) throws JsonProcessingException {
    return getFriendlyObjectMapper().readValue(json, clazz);
  }

  public static <T> TypeReference<T> toTypeReference(T obj) {
    return new TypeReference<>() {
    };
  }

}
