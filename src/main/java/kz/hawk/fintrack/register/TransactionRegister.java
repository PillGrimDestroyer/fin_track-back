package kz.hawk.fintrack.register;


import kz.hawk.fintrack.model.request.TransactionFilteredDataSliceRequest;
import kz.hawk.fintrack.model.request.TransactionRequest;
import kz.hawk.fintrack.model.request.UpdateTransactionRequest;
import kz.hawk.fintrack.model.response.TransactionResponse;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * @author megam
 * @since 18.01.2026 8:13
 */
public interface TransactionRegister {

  void addTransaction(TransactionRequest request);

  List<TransactionResponse> filteredDataSlice(TransactionFilteredDataSliceRequest request);

  void deleteTransaction(UUID id);

  void updateTransaction(UUID id, @Valid UpdateTransactionRequest request);
}
