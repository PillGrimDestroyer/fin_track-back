package kz.hawk.fintrack.controller;


import kz.hawk.fintrack.model.request.TransactionRequest;
import kz.hawk.fintrack.model.response.RecentTransactionResponse;
import kz.hawk.fintrack.register.TransactionRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

  @GetMapping("/recent")
  public @ResponseBody List<RecentTransactionResponse> recent() {
    return transactionRegister.recent();
  }

}
