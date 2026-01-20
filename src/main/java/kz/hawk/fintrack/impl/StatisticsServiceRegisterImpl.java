package kz.hawk.fintrack.impl;


import kz.hawk.fintrack.dao.StatisticsDao;
import kz.hawk.fintrack.model.enums.Transaction;
import kz.hawk.fintrack.model.response.SpendByCategoryResponse;
import kz.hawk.fintrack.register.SessionRegister;
import kz.hawk.fintrack.register.StatisticsServiceRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author megam
 * @since 20.01.2026 13:27
 */
@Component
@RequiredArgsConstructor
public class StatisticsServiceRegisterImpl implements StatisticsServiceRegister {

  private final StatisticsDao   statisticsDao;
  private final SessionRegister sessionRegister;

  @Override
  public List<SpendByCategoryResponse> spendByCategory() {
    return statisticsDao.spendByCategory(sessionRegister.currentUserId(), Transaction.EXPENSE);
  }

}
