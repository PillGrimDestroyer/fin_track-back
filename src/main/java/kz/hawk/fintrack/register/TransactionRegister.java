package kz.hawk.fintrack.register;


import kz.hawk.fintrack.model.request.TransactionRequest;

/**
 * @author megam
 * @since 18.01.2026 8:13
 */
public interface TransactionRegister {

  void addTransaction(TransactionRequest request);

}
