package kz.hawk.fintrack.model;


import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * @author megam
 * @since 18.01.2026 14:05
 */
public class MultiReadHttpServletRequestWrapper extends HttpServletRequestWrapper {

  private String requestBody;

  public MultiReadHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
    super(request);
    this.requestBody = getBodyFromRequest(request);
  }

  @Override
  public ServletInputStream getInputStream() throws IOException {
    final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(requestBody.getBytes(StandardCharsets.UTF_8));
    return new ServletInputStream() {
      @Override
      public boolean isFinished() {
        return byteArrayInputStream.available() == 0;
      }

      @Override
      public boolean isReady() {
        return true;
      }

      @Override
      public void setReadListener(ReadListener readListener) {
        // No implementation needed
      }

      @Override
      public int read() throws IOException {
        return byteArrayInputStream.read();
      }
    };
  }

  @Override
  public BufferedReader getReader() throws IOException {
    return new BufferedReader(new InputStreamReader(this.getInputStream(), StandardCharsets.UTF_8));
  }

  public String getRequestBody() {
    return this.requestBody;
  }

  public void setRequestBody(String body) {
    this.requestBody = body;
  }

  private String getBodyFromRequest(HttpServletRequest request) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8))) {
      return reader.lines().collect(Collectors.joining(System.lineSeparator()));
    }
  }

}
