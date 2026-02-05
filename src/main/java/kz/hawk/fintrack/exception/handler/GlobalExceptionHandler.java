package kz.hawk.fintrack.exception.handler;

import jakarta.validation.constraints.NotNull;
import kotlin.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @NotNull
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
    @NotNull MethodArgumentNotValidException ex,
    @NotNull HttpHeaders headers,
    @NotNull HttpStatusCode status,
    @NotNull WebRequest request
  ) {
    var body = new HashMap<String, List<String>>();

    var errors = ex.getBindingResult()
                   .getFieldErrors()
                   .stream()
                   .map(x -> new Pair<>(x.getDefaultMessage(), x.toString()))
                   .toList();

    body.put("errors", errors.stream().map(Pair::getFirst).collect(Collectors.toList()));
    log.error("Validation error: {}", errors.stream().map(Pair::getSecond).collect(Collectors.toList()));

    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }

}
