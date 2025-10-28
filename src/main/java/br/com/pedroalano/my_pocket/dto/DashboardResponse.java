package br.com.pedroalano.my_pocket.dto;

import java.math.BigDecimal;

public record DashboardResponse(
        Integer month,
        Long categoryId,
        String categoryName,
        BigDecimal totalPlanned,
        BigDecimal totalActual,
        BigDecimal difference
) {
}
