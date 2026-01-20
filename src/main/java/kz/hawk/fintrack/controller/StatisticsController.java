package kz.hawk.fintrack.controller;


import kz.hawk.fintrack.model.response.BalanceSummaryResponse;
import kz.hawk.fintrack.model.response.SpendByCategoryResponse;
import kz.hawk.fintrack.register.StatisticsServiceRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author megam
 * @since 20.01.2026 13:24
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class StatisticsController {

  private final StatisticsServiceRegister statisticsService;

  @GetMapping("/spend-by-category")
  public @ResponseBody List<SpendByCategoryResponse> spendByCategory() {
    return statisticsService.spendByCategory();
  }

  @GetMapping("/balance-summary")
  public @ResponseBody BalanceSummaryResponse balanceSummary() {
    return statisticsService.balanceSummary();
  }

}
