package kz.hawk.fintrack.dao;


import kz.hawk.fintrack.model.dao.CategoryDto;
import org.apache.ibatis.annotations.Insert;

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

}
