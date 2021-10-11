package kz.hawk.risesecurity.controller;

import kz.hawk.risesecurity.in_service.RequestDefenderInService;
import kz.hawk.risesecurity.model.in_service.request.PrepareRequestInService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/security")
@RequiredArgsConstructor
@Slf4j
public class SecurityController {

  private final RequestDefenderInService requestDefenderInService;

  @PostMapping("/prepare")
  public @ResponseBody
  ResponseEntity<?> prepare(@Valid @RequestBody PrepareRequestInService request) {
    return ResponseEntity.ok(requestDefenderInService.prepare(request));
  }

}
