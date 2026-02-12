package kz.hawk.fintrack.register;


import kz.hawk.fintrack.model.request.CategoryRequest;
import kz.hawk.fintrack.model.response.CategoryResponse;

import java.util.List;
import java.util.UUID;

/**
 * @author megam
 * @since 19.01.2026 16:31
 */
public interface CategoryRegister {

  List<CategoryResponse> all();

  void delete(UUID id);

  CategoryResponse update(UUID id, CategoryRequest request);

  void add(CategoryRequest request);

}
