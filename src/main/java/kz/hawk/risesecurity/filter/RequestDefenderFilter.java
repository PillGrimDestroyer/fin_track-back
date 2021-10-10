package kz.hawk.risesecurity.filter;

import kz.hawk.risesecurity.config.RequestDefenderConfig;
import kz.hawk.risesecurity.in_service.RequestDefenderInService;
import kz.hawk.risesecurity.model.in_service.request.CheckRequestInService;
import kz.hawk.risesecurity.util.Json;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static kz.hawk.risesecurity.beans.SecurityConfig.FREE_ACCESS_URLS;

@Component
@RequiredArgsConstructor
public class RequestDefenderFilter extends OncePerRequestFilter {

  private final RequestDefenderConfig    requestDefenderConfig;
  private final RequestDefenderInService requestDefenderInService;

  private final List<String> excludeUrls = new ArrayList<>();

  @PostConstruct
  private void init() {
    excludeUrls.addAll(Arrays.asList(FREE_ACCESS_URLS));
    excludeUrls.add("/security/prepare");
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    String path = request.getRequestURI();

    return excludeUrls.stream().anyMatch(Predicate.isEqual(path));
  }

  @SneakyThrows
  @Override
  protected void doFilterInternal(
    @NotNull HttpServletRequest request,
    @NotNull HttpServletResponse response,
    @NotNull FilterChain filterChain
  ) {
    String body;

    if ("post".equalsIgnoreCase(request.getMethod())) {
      body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
      body = body.isBlank() ? "{}" : body;
    } else {
      body = Json.toJson(request.getParameterMap());
    }

    try {
      var mapBody        = Json.toObject(body, Json.toTypeReference(new HashMap<String, Object>()));
      var securityHeader = getHeaderOrThrow(requestDefenderConfig.securityHeader(), request);
      var tokenHeader    = getHeaderOrThrow(requestDefenderConfig.requestTokenHeader(), request);

      var checkRequest = CheckRequestInService.builder()
                                              .header(securityHeader)
                                              .body(mapBody)
                                              .secretId(UUID.fromString(tokenHeader))
                                              .build();

      var checkResponse = requestDefenderInService.check(checkRequest);

      if (!checkResponse.isValid()) {
        throw new RuntimeException(checkResponse.getErrorMsg());
      }

      filterChain.doFilter(request, response);
    } catch (Exception e) {
      response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getLocalizedMessage());

      logger.error(e.getMessage());
    }
  }

  private String getHeaderOrThrow(String headerName, HttpServletRequest request) {
    return Optional.ofNullable(request.getHeader(headerName))
                   .orElseThrow(() -> new RuntimeException("zJ6cSF121Ra3M :: Header '" + headerName + "' not found"));
  }
}
