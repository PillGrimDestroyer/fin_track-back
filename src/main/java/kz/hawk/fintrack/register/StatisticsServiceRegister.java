package kz.hawk.fintrack.register;


import kz.hawk.fintrack.model.response.BalanceSummaryResponse;
import kz.hawk.fintrack.model.response.SpendByCategoryResponse;
import kz.hawk.fintrack.model.response.WeeklySpendingResponse;

import java.util.List;

/**
 * @author megam
 * @since 20.01.2026 13:27
 */
public interface StatisticsServiceRegister {

  List<SpendByCategoryResponse> spendByCategory();

  BalanceSummaryResponse balanceSummary();

  List<WeeklySpendingResponse> weeklySpending();

}
