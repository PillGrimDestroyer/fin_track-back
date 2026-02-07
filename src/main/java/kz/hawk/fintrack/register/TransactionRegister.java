package kz.hawk.fintrack.register;


import kz.hawk.fintrack.model.request.TransactionFilteredDataSliceRequest;
import kz.hawk.fintrack.model.request.TransactionRequest;
import kz.hawk.fintrack.model.request.UpdateTransactionRequest;
import kz.hawk.fintrack.model.response.TransactionDataSliceResponse;
import kz.hawk.fintrack.model.response.TransactionResponse;

import javax.validation.Valid;
import java.util.UUID;

/**
 * @author megam
 * @since 18.01.2026 8:13
 */
public interface TransactionRegister {

  void addTransaction(TransactionRequest request);

  TransactionDataSliceResponse filteredDataSlice(TransactionFilteredDataSliceRequest request);

  void deleteTransaction(UUID id);

  TransactionResponse updateTransaction(UUID id, @Valid UpdateTransactionRequest request);
}
