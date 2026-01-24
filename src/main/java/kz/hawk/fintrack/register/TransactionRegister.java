package kz.hawk.fintrack.register;


import kz.hawk.fintrack.model.request.TransactionRequest;
import kz.hawk.fintrack.model.response.TransactionResponse;

import java.util.List;

/**
 * @author megam
 * @since 18.01.2026 8:13
 */
public interface TransactionRegister {

  void addTransaction(TransactionRequest request);

  List<TransactionResponse> recent();
}
