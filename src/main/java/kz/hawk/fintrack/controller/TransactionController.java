package kz.hawk.fintrack.controller;


import kz.hawk.fintrack.model.request.TransactionFilteredDataSliceRequest;
import kz.hawk.fintrack.model.request.TransactionRequest;
import kz.hawk.fintrack.model.request.UpdateTransactionRequest;
import kz.hawk.fintrack.model.response.TransactionDataSliceResponse;
import kz.hawk.fintrack.model.response.TransactionResponse;
import kz.hawk.fintrack.register.TransactionRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

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

  @GetMapping("/filtered-data-slice")
  public @ResponseBody TransactionDataSliceResponse filteredDataSlice(TransactionFilteredDataSliceRequest request) {
    return transactionRegister.filteredDataSlice(request);
  }

  @DeleteMapping("/{id}")
  public void deleteTransaction(@PathVariable UUID id) {
    transactionRegister.deleteTransaction(id);
  }

  @PutMapping("/{id}")
  public @ResponseBody TransactionResponse updateTransaction(@PathVariable UUID id, @Valid @RequestBody UpdateTransactionRequest request) {
    return transactionRegister.updateTransaction(id, request);
  }

}
