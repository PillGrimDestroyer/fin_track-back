package kz.hawk.risesecurity.controller;

import kz.hawk.risesecurity.annotations.AdminPermission;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/test")
public class TestController {

  @GetMapping("/user")
  public ResponseEntity<?> testUser() {
    var response = new HashMap<>();

    response.put("test", "testUser is ok!");

    return ResponseEntity.ok(response);
  }

  @GetMapping("/admin")
  @AdminPermission
  public ResponseEntity<?> testAdmin() {
    var response = new HashMap<>();

    response.put("test", "testAdmin is ok!");

    return ResponseEntity.ok(response);
  }

}
