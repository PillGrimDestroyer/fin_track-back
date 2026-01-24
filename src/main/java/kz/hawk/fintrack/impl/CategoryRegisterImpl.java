package kz.hawk.fintrack.impl;


import kz.hawk.fintrack.dao.CategoryDao;
import kz.hawk.fintrack.mapper.CategoryMapper;
import kz.hawk.fintrack.model.response.CategoryResponse;
import kz.hawk.fintrack.register.CategoryRegister;
import kz.hawk.fintrack.register.SessionRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author megam
 * @since 19.01.2026 16:31
 */
@Component
@RequiredArgsConstructor
public class CategoryRegisterImpl implements CategoryRegister {

  private final CategoryDao     categoryDao;
  private final SessionRegister sessionRegister;
  private final CategoryMapper  categoryMapper;

  @Override
  public List<CategoryResponse> all() {
    return categoryDao.getAllByUserId(sessionRegister.currentUserId()).stream()
                      .map(categoryMapper::toResponse)
                      .collect(Collectors.toList());
  }

}
