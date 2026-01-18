package kz.hawk.fintrack.controller;


import kz.hawk.fintrack.model.request.TransactionRequest;
import kz.hawk.fintrack.register.TransactionRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author megam
 * @since 18.01.2026 8:03
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

  private final TransactionRegister transactionRegister;

  @PostMapping
  public void addTransaction(@Valid @RequestBody TransactionRequest request) {
    transactionRegister.addTransaction(request);
  }

}
