package kz.hawk.fintrack.mapper;


import kz.hawk.fintrack.beans.MapperConfiguration;
import kz.hawk.fintrack.model.dao.CategoryDto;
import kz.hawk.fintrack.model.dao.UserDto;
import kz.hawk.fintrack.model.request.CategoryRequest;
import kz.hawk.fintrack.model.response.CategoryResponse;
import kz.hawk.fintrack.register.SessionRegister;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * @author megam
 * @since 24.01.2026 16:26
 */
@Mapper(config = MapperConfiguration.class)
public interface CategoryMapper {

  CategoryResponse toResponse(CategoryDto categoryDto);

  CategoryDto toDto(CategoryRequest request, @Context SessionRegister sessionRegister);

  @AfterMapping
  default void refineDto(CategoryRequest request, @MappingTarget CategoryDto dto, @Context SessionRegister sessionRegister) {
    UserDto userDto = new UserDto();
    userDto.setId(sessionRegister.currentUserId());

    dto.setUser(userDto);
  }

}
