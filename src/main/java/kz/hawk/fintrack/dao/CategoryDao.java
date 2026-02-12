package kz.hawk.fintrack.dao;


import kz.hawk.fintrack.model.dao.CategoryDto;
import kz.hawk.fintrack.model.request.CategoryRequest;
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
    "insert into categories (name_ru, name_en, icon, user_id, is_default) " +
    "values (#{nameRu}, #{nameEn}, #{icon}, #{user.id}, #{isDefault})"
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
    @Result(column = "updated_at", property = "updatedAt"),
    @Result(column = "is_default", property = "isDefault"),
    @Result(column = "user_id", property = "user", one = @One(select = "kz.hawk.fintrack.dao.UserDao.getById", fetchType = FetchType.LAZY))
  })
  List<CategoryDto> getAllByUserId(@Param("userId") UUID userId);

  @Select(
    "select exists(select 1 from categories where id = #{id} and user_id = #{userId})"
  )
  boolean isExist(@Param("id") UUID id, @Param("userId") UUID userId);

  @Select(
    "select is_default from categories where id = #{id} and user_id = #{userId}"
  )
  boolean isDefault(@Param("id") UUID id, @Param("userId") UUID userId);

  @Delete(
    "delete from categories where id = #{id}"
  )
  void delete(@Param("id") UUID id);

  @Update("""
          <script>
              update categories
              <set>
                  <if test="id != null">updated_at = now(),</if>
                  <if test="request.nameRu != null">name_ru = #{request.nameRu},</if>
                  <if test="request.nameEn != null">name_en = #{request.nameEn},</if>
                  <if test="request.icon != null">icon = #{request.icon}</if>
              </set>
              where id = #{id}
          </script>
          """)
  void update(@Param("id") UUID id, @Param("request") CategoryRequest request);

  @Select(
    "select * from categories where id = #{id}"
  )
  @ResultMap("categoryResultMap")
  CategoryDto getById(@Param("id") UUID id);

}
