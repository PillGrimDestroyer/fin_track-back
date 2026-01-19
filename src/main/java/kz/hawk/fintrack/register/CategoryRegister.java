package kz.hawk.fintrack.register;


import kz.hawk.fintrack.model.response.CategoryResponse;

import java.util.List;

/**
 * @author megam
 * @since 19.01.2026 16:31
 */
public interface CategoryRegister {

  List<CategoryResponse> all();

}
