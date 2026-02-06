package kz.hawk.fintrack.dao;


import kz.hawk.fintrack.model.dao.CategoryDto;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

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
  @Results(id = "categoryResultMap", value = {
    @Result(column = "id", property = "id", id = true),
    @Result(column = "name_ru", property = "nameRu"),
    @Result(column = "name_en", property = "nameEn"),
    @Result(column = "icon", property = "icon"),
    @Result(column = "created_at", property = "createdAt"),
    @Result(column = "user_id", property = "user", one = @One(select = "kz.hawk.fintrack.dao.UserDao.getById", fetchType = FetchType.LAZY))
  })
  List<CategoryDto> getAllByUserId(@Param("userId") UUID userId);

  @Select("select exists(select 1 from categories where id = #{id} and user_id = #{userId})")
  boolean isExist(@Param("id") UUID id, @Param("userId") UUID userId);

}
