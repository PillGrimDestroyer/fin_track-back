package kz.hawk.fintrack.dao;


import kz.hawk.fintrack.model.dao.TransactionDto;
import kz.hawk.fintrack.model.enums.Transaction;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
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

  // &lt;= is <=
  // &gt;= is >=
  // &amp; is &
  @Select("""
          <script>
          select t.*,
                 c.id as c_id, c.name_ru as c_name_ru, c.name_en as c_name_en, c.icon as c_icon, c.user_id as c_user_id, c.created_at as c_created_at
              from transactions t
               left join categories c on c.id = t.category_id
             where t.user_id = #{userId}
          <if test="type != null">and t.type = #{type}</if>
          <if test="description != null">and t.description like '%' || #{description} || '%'</if>
          <if test="maxAmount != null">and t.amount &lt;= #{maxAmount}</if>
          <if test="minAmount != null">and t.amount &gt;= #{minAmount}</if>
          <if test="rangeFrom != null">and t.transaction_date &gt;= #{rangeFrom}</if>
          <if test="rangeTo != null">and t.transaction_date &lt;= #{rangeTo}</if>
          <if test="category != null">and c.name_en = #{category}</if>
          order by t.transaction_date desc
          limit #{limit}
          offset #{offset}
          </script>
          """)
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
  List<TransactionDto> filteredDataSlice(
    @Param("userId") @NotNull UUID userId,
    @Param("type") @Nullable Transaction transactionType,
    @Param("description") @Nullable String description,
    @Param("maxAmount") @Nullable BigDecimal maxAmount,
    @Param("minAmount") @Nullable BigDecimal minAmount,
    @Param("rangeFrom") @Nullable OffsetDateTime transactionRangeFrom,
    @Param("rangeTo") @Nullable OffsetDateTime transactionRangeTo,
    @Param("category") @Nullable String categoryNameEn,
    @Param("limit") int limit,
    @Param("offset") int offset
  );

}
