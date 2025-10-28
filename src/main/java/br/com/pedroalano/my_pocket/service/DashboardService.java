package br.com.pedroalano.my_pocket.service;

import br.com.pedroalano.my_pocket.dto.DashboardAggregationDTO;
import br.com.pedroalano.my_pocket.dto.DashboardByTypeResponse;
import br.com.pedroalano.my_pocket.dto.DashboardResponse;
import br.com.pedroalano.my_pocket.enumtype.TransactionStatus;
import br.com.pedroalano.my_pocket.enumtype.TransactionType;
import br.com.pedroalano.my_pocket.model.Transaction;
import br.com.pedroalano.my_pocket.repository.DashboardRepository;
import br.com.pedroalano.my_pocket.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final TransactionRepository transactionRepository;

    private final DashboardRepository dashboardRepository;

    public List<DashboardAggregationDTO> get(Integer year, Long accountId) {
        return dashboardRepository.aggregateByTypeAndMonthFilterByMonthAndYearAndAccount(1,year,accountId);
    }

    public DashboardByTypeResponse getByTypeAndMonthFilterByMonthAndYearAndAccount(Integer month, Integer year, Long accountId) {
        List<DashboardAggregationDTO> expense = dashboardRepository.formatedByTypeAndMonthFilterByMonthAndYearAndAccount(month,year,accountId,TransactionType.EXPENSE.toString());
        List<DashboardAggregationDTO> income = dashboardRepository.formatedByTypeAndMonthFilterByMonthAndYearAndAccount(month,year,accountId,TransactionType.INCOME.toString());

        return new DashboardByTypeResponse(income, expense);
    }
}

