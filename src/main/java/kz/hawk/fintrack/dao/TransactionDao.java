package kz.hawk.fintrack.dao;


import kz.hawk.fintrack.model.dao.TransactionDto;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.UUID;

/**
 * @author megam
 * @since 18.01.2026 7:44
 */
@Mapper
public interface TransactionDao {

  @Insert(
    "insert into transactions " +
      "(amount, type, description, transaction_date, user_id, category_id) " +
      "values (#{amount}, #{type}, #{description}, #{transactionDate}, #{user.id}, #{category.id})"
  )
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insert(TransactionDto transaction);

  @Select(
    "select " +
      "t.*, " +
      "c.id as c_id, c.name_ru as c_name_ru, c.name_en as c_name_en, c.icon as c_icon, c.user_id as c_user_id, c.created_at as c_created_at " +
      "from transactions t " +
      "left join categories c on t.category_id = c.id " +
      "where t.user_id = #{userId} " +
      "order by t.transaction_date desc " +
      "limit 10"
  )
  @Results({
    @Result(column = "id", property = "id", id = true),
    @Result(column = "amount", property = "amount"),
    @Result(column = "type", property = "type"),
    @Result(column = "description", property = "description"),
    @Result(column = "transaction_date", property = "transactionDate"),
    @Result(column = "updated_at", property = "updatedAt"),
    @Result(column = "created_at", property = "createdAt"),
    @Result(column = "c_id", property = "category", one = @One(resultMap = "kz.hawk.fintrack.dao.CategoryDao.categoryResultMap", columnPrefix = "c_")),
    @Result(column = "user_id", property = "user", one = @One(select = "kz.hawk.fintrack.dao.UserDao.getById", fetchType = FetchType.LAZY))
  })
  List<TransactionDto> last10ByUserId(@Param("userId") UUID userId);

}
