package kz.hawk.fintrack.register;


import kz.hawk.fintrack.model.response.SpendByCategoryResponse;

import java.util.List;

/**
 * @author megam
 * @since 20.01.2026 13:27
 */
public interface StatisticsServiceRegister {
  List<SpendByCategoryResponse> spendByCategory();
}
