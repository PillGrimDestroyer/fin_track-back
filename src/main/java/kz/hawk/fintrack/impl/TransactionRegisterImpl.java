package kz.hawk.fintrack.impl;


import kz.hawk.fintrack.dao.CategoryDao;
import kz.hawk.fintrack.dao.TransactionDao;
import kz.hawk.fintrack.mapper.TransactionMapper;
import kz.hawk.fintrack.model.dao.CategoryDto;
import kz.hawk.fintrack.model.dao.TransactionDto;
import kz.hawk.fintrack.model.dao.UserDto;
import kz.hawk.fintrack.model.request.TransactionFilteredDataSliceRequest;
import kz.hawk.fintrack.model.request.TransactionRequest;
import kz.hawk.fintrack.model.request.UpdateTransactionRequest;
import kz.hawk.fintrack.model.response.TransactionResponse;
import kz.hawk.fintrack.register.SessionRegister;
import kz.hawk.fintrack.register.TransactionRegister;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
  private final CategoryDao       categoryDao;

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
  public List<TransactionResponse> filteredDataSlice(TransactionFilteredDataSliceRequest request) {
    return transactionDao.filteredDataSlice(
                           sessionRegister.currentUserId(),
                           request.getTransactionType(),
                           trimIfNotBlank(request.getDescription()),
                           request.getMaxAmount(),
                           request.getMinAmount(),
                           request.getTransactionRangeFrom(),
                           request.getTransactionRangeTo(),
                           trimIfNotBlank(request.getCategoryNameEn()),
                           request.getLimit(),
                           request.getOffset()
                         ).stream()
                         .map(transactionMapper::toResponse)
                         .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public void deleteTransaction(UUID id) {
    boolean isBelongToUser = transactionDao.isBelongToUser(id, sessionRegister.currentUserId());

    if (!isBelongToUser) {
      throw new IllegalArgumentException("Transaction does not belong to the current user");
    }

    transactionDao.delete(id);
  }

  @Override
  @Transactional
  public void updateTransaction(UUID id, UpdateTransactionRequest request) {
    boolean isBelongToUser = transactionDao.isBelongToUser(id, sessionRegister.currentUserId());

    if (!isBelongToUser) {
      throw new IllegalArgumentException("Transaction does not belong to the current user");
    }

    boolean isCategoryExist = Optional.ofNullable(request.getCategoryId())
                                      .map(categoryId -> categoryDao.isExist(categoryId, sessionRegister.currentUserId()))
                                      .orElse(false);

    if (!isCategoryExist) {
      throw new IllegalArgumentException("Category does not exist");
    }

    transactionDao.update(id, request);
  }

  private @Nullable String trimIfNotBlank(@Nullable String value) {
    return Optional.ofNullable(value)
                   .filter(x -> !x.isBlank())
                   .map(String::trim)
                   .orElse(null);
  }

}
