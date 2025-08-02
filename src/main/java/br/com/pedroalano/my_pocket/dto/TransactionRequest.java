package br.com.pedroalano.my_pocket.dto;

import br.com.pedroalano.my_pocket.enumtype.TransactionStatus;
import br.com.pedroalano.my_pocket.enumtype.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionRequest(
        @NotNull LocalDate date,
        @NotNull BigDecimal value,
        @NotNull @NotBlank String description,
        @NotNull TransactionType type,
        @NotNull TransactionStatus status,
        @NotNull Long category,
        @NotNull Long account
) {
}
