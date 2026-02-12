package kz.hawk.fintrack.impl;


import kz.hawk.fintrack.dao.CategoryDao;
import kz.hawk.fintrack.dao.TransactionDao;
import kz.hawk.fintrack.mapper.CategoryMapper;
import kz.hawk.fintrack.model.dao.CategoryDto;
import kz.hawk.fintrack.model.request.CategoryRequest;
import kz.hawk.fintrack.model.response.CategoryResponse;
import kz.hawk.fintrack.register.CategoryRegister;
import kz.hawk.fintrack.register.SessionRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
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
  private final TransactionDao  transactionDao;

  @Override
  public List<CategoryResponse> all() {
    return categoryDao.getAllByUserId(sessionRegister.currentUserId()).stream()
                      .map(categoryMapper::toResponse)
                      .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public void delete(UUID id) {
    boolean isExist = categoryDao.isExist(id, sessionRegister.currentUserId());

    if (!isExist) {
      throw new IllegalArgumentException("Category not found");
    }

    boolean isDefault = categoryDao.isDefault(id, sessionRegister.currentUserId());

    if (isDefault) {
      throw new IllegalArgumentException("Default category can not be deleted");
    }

    CategoryDto defaultCategory = categoryDao.getDefault(sessionRegister.currentUserId());

    transactionDao.resetCategoryToDefault(id, defaultCategory.getId(), sessionRegister.currentUserId());
    categoryDao.delete(id);
  }

  @Override
  @Transactional
  public CategoryResponse update(UUID id, CategoryRequest request) {
    boolean isExist = categoryDao.isExist(id, sessionRegister.currentUserId());

    if (!isExist) {
      throw new IllegalArgumentException("Category not found");
    }

    boolean isDefault = categoryDao.isDefault(id, sessionRegister.currentUserId());

    if (isDefault) {
      throw new IllegalArgumentException("Default category can not be changed");
    }

    categoryDao.update(id, request);
    return categoryMapper.toResponse(categoryDao.getById(id));
  }

  @Override
  @Transactional
  public CategoryResponse add(CategoryRequest request) {
    CategoryDto dto = categoryMapper.toDto(request, sessionRegister);

    categoryDao.createCategory(dto);

    return categoryMapper.toResponse(categoryDao.getById(dto.getId()));
  }

}
