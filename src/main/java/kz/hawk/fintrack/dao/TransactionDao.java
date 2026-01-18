package kz.hawk.fintrack.dao;


import kz.hawk.fintrack.model.dao.TransactionDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * @author megam
 * @since 18.01.2026 7:44
 */
public interface TransactionDao {

  @Insert(
    "insert into transactions " +
      "(amount, type, description, transaction_date, user_id, category_id) " +
      "values (#{amount}, #{type}, #{description}, #{transactionDate}, #{user.id}, #{category.id})"
  )
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insert(TransactionDto transaction);
}
