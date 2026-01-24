package kz.hawk.fintrack.mapper;


import kz.hawk.fintrack.beans.MapperConfiguration;
import kz.hawk.fintrack.model.dao.CategoryDto;
import kz.hawk.fintrack.model.response.CategoryResponse;
import org.mapstruct.Mapper;

/**
 * @author megam
 * @since 24.01.2026 16:26
 */
@Mapper(config = MapperConfiguration.class)
public interface CategoryMapper {

  CategoryResponse toResponse(CategoryDto categoryDto);

}
