package kz.hawk.fintrack.dao;


import kz.hawk.fintrack.model.dao.CategoryDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.UUID;

/**
 * @author megam
 * @since 13.01.2026 14:47
 */
public interface CategoryDao {

  @Insert(
    "insert into categories (name_ru, name_en, icon, user_id) " +
      "values (#{nameRu}, #{nameEn}, #{icon}, #{user.id})"
  )
  void createCategory(CategoryDto category);

  @Select(
    "select * from categories where user_id = #{userId}"
  )
  List<CategoryDto> getAllByUserId(@Param("userId") UUID userId);
}
