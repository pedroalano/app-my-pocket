package br.com.pedroalano.my_pocket.controller;

import br.com.pedroalano.my_pocket.dto.DashboardByTypeResponse;
import br.com.pedroalano.my_pocket.service.DashboardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardByTypeResponse> getDashboardAggregation(@RequestParam(value = "month", required = true) Integer month,@RequestParam(value = "year", required = true) Integer year, @RequestParam(value = "account", required = true) Long accountId) {
        return ResponseEntity.ok(dashboardService.getByTypeAndMonthFilterByMonthAndYearAndAccount(month,year, accountId));
    }
}
