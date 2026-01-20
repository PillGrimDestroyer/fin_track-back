package kz.hawk.fintrack.dao;


import kz.hawk.fintrack.model.enums.Transaction;
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
      "and t.type = #{type} " +
      "and t.transaction_date >= (now() - interval '1 month') " +
      "GROUP BY c.name_ru, c.name_en " +
      "order by total_amount desc " +
      "limit 3"
  )
  List<SpendByCategoryResponse> spendByCategory(@Param("userId") UUID userId, @Param("type") Transaction type);

}
