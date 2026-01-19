package kz.hawk.fintrack.controller;


import kz.hawk.fintrack.model.response.CategoryResponse;
import kz.hawk.fintrack.register.CategoryRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
