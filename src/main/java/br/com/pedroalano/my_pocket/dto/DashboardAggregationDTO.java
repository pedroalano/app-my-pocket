package br.com.pedroalano.my_pocket.dto;

import br.com.pedroalano.my_pocket.enumtype.TransactionStatus;
import br.com.pedroalano.my_pocket.enumtype.TransactionType;

import java.math.BigDecimal;

public interface DashboardAggregationDTO {
        Integer getCategoryId();
        Integer getMonth();
        String getCategoryName();
        String getType();
        BigDecimal getTotalPlanned();
        BigDecimal getTotalActual();
        BigDecimal getDifference();
}
