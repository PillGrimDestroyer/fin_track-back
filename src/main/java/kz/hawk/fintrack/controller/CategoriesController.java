package kz.hawk.fintrack.controller;


import kz.hawk.fintrack.model.request.CategoryRequest;
import kz.hawk.fintrack.model.response.CategoryResponse;
import kz.hawk.fintrack.register.CategoryRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author megam
 * @since 19.01.2026 16:25
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoriesController {

  private final CategoryRegister categoryRegister;

  @GetMapping
  public @ResponseBody List<CategoryResponse> all() {
    return categoryRegister.all();
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable UUID id) {
    categoryRegister.delete(id);
  }

  @PutMapping("/{id}")
  public @ResponseBody CategoryResponse update(@PathVariable UUID id, @RequestBody CategoryRequest request) {
    return categoryRegister.update(id, request);
  }

  @PostMapping
  public void add(@RequestBody CategoryRequest request) {
    categoryRegister.add(request);
  }

}
