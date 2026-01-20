package kz.hawk.fintrack.dao;


import kz.hawk.fintrack.model.response.BalanceSummaryResponse;
import kz.hawk.fintrack.model.response.SpendByCategoryResponse;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.UUID;

/**
 * @author megam
 * @since 20.01.2026 13:29
 */
public interface StatisticsDao {

  @Select(
    "SELECT c.name_ru as category_name_ru, c.name_en as category_name_en, SUM(t.amount) as total_amount " +
      "FROM transactions t " +
      "LEFT JOIN categories c on c.id = t.category_id " +
      "where t.user_id = #{userId} " +
      "and t.type = 'EXPENSE' " +
      "and t.transaction_date >= (now() - interval '1 month') " +
      "GROUP BY c.name_ru, c.name_en " +
      "order by total_amount desc " +
      "limit 3"
  )
  List<SpendByCategoryResponse> spendByCategory(@Param("userId") UUID userId);

  @Select("""
          SELECT
              SUM(CASE WHEN type = 'INCOME' THEN amount ELSE -amount END) as total,
              SUM(CASE WHEN type = 'INCOME' AND transaction_date >= date_trunc('month', now()) THEN amount ELSE 0 END) as income,
              SUM(CASE WHEN type = 'EXPENSE' AND transaction_date >= date_trunc('month', now()) THEN amount ELSE 0 END) as expenses
          FROM transactions
          WHERE user_id = #{userId}
          """)
  BalanceSummaryResponse balanceSummary(@Param("userId") UUID uuid);
}
