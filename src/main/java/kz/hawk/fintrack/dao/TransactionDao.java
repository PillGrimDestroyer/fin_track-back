package kz.hawk.fintrack.dao;


import kz.hawk.fintrack.model.TransactionDataSliceFilter;
import kz.hawk.fintrack.model.dao.TransactionDto;
import kz.hawk.fintrack.model.request.UpdateTransactionRequest;
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
          offset (#{page} - 1) * #{limit}
          </script>
          """)
  @Results(id = "transactionCategoryJoiningResultMap", value = {
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
  List<TransactionDto> filteredDataSlice(TransactionDataSliceFilter filter);

  @Select("""
          <script>
          select ceil(count(t.id)::numeric / #{limit})
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
          </script>
          """)
  int filteredDataSliceTotalPages(TransactionDataSliceFilter filter);

  @Select("""
          select exists (
            select 1 from transactions where id = #{transactionId} and user_id = #{userId}
          )
          """)
  boolean isBelongToUser(@Param("transactionId") UUID transactionId, @Param("userId") UUID userId);

  @Delete("delete from transactions where id = #{id}")
  void delete(@Param("id") UUID id);

  @Update("""
          <script>
              update transactions
              <set>
                  <if test="id != null">updated_at = now(),</if>
                  <if test="request.description != null">description = #{request.description},</if>
                  <if test="request.amount != null">amount = #{request.amount},</if>
                  <if test="request.transactionDate != null">transaction_date = #{request.transactionDate},</if>
                  <if test="request.categoryId != null">category_id = #{request.categoryId},</if>
                  <if test="request.type != null">type = #{request.type}</if>
              </set>
              where id = #{id}
          </script>
          """)
  void update(@Param("id") UUID id, @Param("request") UpdateTransactionRequest request);

  @Select("""
          select t.*,
              c.id as c_id, c.name_ru as c_name_ru, c.name_en as c_name_en, c.icon as c_icon, c.user_id as c_user_id, c.created_at as c_created_at
          from transactions t
              left join categories c on c.id = t.category_id
          where t.id = #{id}
          """)
  @ResultMap("transactionCategoryJoiningResultMap")
  TransactionDto getById(@Param("id") UUID id);

  @Select(
    "select * from transactions where category_id = #{id}"
  )
  List<TransactionDto> getAllByCategoryId(@Param("id") UUID id);

  @Update("""
          update transactions set category_id = #{categoryId} where id in (#{list})
          """)
  void resetCategoryToDefault(@Param("list") List<UUID> transactionIds, @Param("categoryId") UUID defaultCategoryId);

}
