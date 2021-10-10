package kz.hawk.risesecurity.in_service.impl;

import kz.hawk.risesecurity.in_service.RequestDefenderInService;
import kz.hawk.risesecurity.model.in_service.RequestOptionsInService;
import kz.hawk.risesecurity.model.in_service.request.CheckRequestInService;
import kz.hawk.risesecurity.model.in_service.request.PrepareRequestInService;
import kz.hawk.risesecurity.model.in_service.response.CheckResponseInService;
import kz.hawk.risesecurity.model.in_service.response.PrepareResponseInService;
import kz.hawk.risesecurity.util.Json;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class RequestDefenderInServiceReal implements RequestDefenderInService {

  private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

  private final OkHttpClient            httpClient;
  private final RequestOptionsInService options;

  @SneakyThrows
  @Override
  public PrepareResponseInService prepare(PrepareRequestInService requestData) {
    var url = getBaseUrlBuilder();
    url.addPathSegment("prepare");

    var request = new Request.Builder()
      .url(url.build())
      .post(RequestBody.create(Json.toJson(requestData), JSON))
      .addHeader("Content-Type", "application/json")
      .build();

    return Json.toObject(makeCall(request), PrepareResponseInService.class);
  }

  @SneakyThrows
  @Override
  public CheckResponseInService check(CheckRequestInService requestData) {
    var url = getBaseUrlBuilder();
    url.addPathSegment("request_check");

    var request = new Request.Builder()
      .url(url.build())
      .post(RequestBody.create(Json.toJson(requestData), JSON))
      .addHeader("Content-Type", "application/json")
      .build();

    return Json.toObject(makeCall(request), CheckResponseInService.class);
  }

  private String checkAndReadResponse(Response res) {
    var    body = res.body();
    String stringBody;

    try {
      if (body != null) {
        stringBody = body.string();
      } else {
        stringBody = res.toString();
      }
    } catch (IOException e) {
      throw new RuntimeException(body.toString());
    }

    if (res.code() != 200) {
      throw new RuntimeException(stringBody);
    }

    return stringBody;
  }

  private HttpUrl.Builder getBaseUrlBuilder() {
    return new HttpUrl.Builder()
      .scheme("http")
      .host(this.options.getHost())
      .port(this.options.getPort())
      .addPathSegment("secret");
  }

  private String makeCall(Request request) {
    try (var execute = this.httpClient.newCall(request).execute()) {
      return checkAndReadResponse(execute);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
