package kz.hawk.fintrack.impl;


import kz.hawk.fintrack.dao.TransactionDao;
import kz.hawk.fintrack.mapper.TransactionMapper;
import kz.hawk.fintrack.model.dao.CategoryDto;
import kz.hawk.fintrack.model.dao.TransactionDto;
import kz.hawk.fintrack.model.dao.UserDto;
import kz.hawk.fintrack.model.request.TransactionRequest;
import kz.hawk.fintrack.model.response.TransactionResponse;
import kz.hawk.fintrack.register.SessionRegister;
import kz.hawk.fintrack.register.TransactionRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author megam
 * @since 18.01.2026 8:13
 */
@Component
@RequiredArgsConstructor
public class TransactionRegisterImpl implements TransactionRegister {

  private final TransactionDao    transactionDao;
  private final SessionRegister   sessionRegister;
  private final TransactionMapper transactionMapper;

  @Override
  public void addTransaction(TransactionRequest request) {
    TransactionDto transaction = new TransactionDto();
    transaction.setTransactionDate(request.getTransactionDate());
    transaction.setType(request.getType());
    transaction.setAmount(request.getAmount());
    transaction.setDescription(request.getDescription());

    CategoryDto category = new CategoryDto();
    category.setId(request.getCategoryId());
    transaction.setCategory(category);

    UserDto user = new UserDto();
    user.setId(sessionRegister.currentUserId());
    transaction.setUser(user);

    transactionDao.insert(transaction);
  }

  @Override
  @Transactional
  public List<TransactionResponse> recent() {
    return transactionDao.limitedSearch(sessionRegister.currentUserId(), null, null, null, null, null, null, 10, 0).stream()
                         .map(transactionMapper::toResponse)
                         .collect(Collectors.toList());
  }

}
