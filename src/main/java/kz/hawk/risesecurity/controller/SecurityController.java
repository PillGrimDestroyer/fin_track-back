package kz.hawk.risesecurity.controller;

import kz.hawk.risesecurity.in_service.RequestDefenderInService;
import kz.hawk.risesecurity.model.in_service.request.PrepareRequestInService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
@RequiredArgsConstructor
@Slf4j
public class SecurityController {

  private final RequestDefenderInService requestDefenderInService;

  @PostMapping("/prepare")
  public @ResponseBody
  ResponseEntity<?> prepare(@RequestBody PrepareRequestInService request) {
    try {
      return ResponseEntity.ok(requestDefenderInService.prepare(request));
    } catch (Exception e) {
      log.error(e.getLocalizedMessage(), e);
      return ResponseEntity.internalServerError().body(null);
    }
  }

}
