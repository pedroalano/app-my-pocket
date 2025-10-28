package br.com.pedroalano.my_pocket.dto;

import java.util.List;

public record DashboardByTypeResponse(
        List<DashboardAggregationDTO> income,
        List<DashboardAggregationDTO> expense
) {
}
