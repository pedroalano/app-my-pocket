package br.com.pedroalano.my_pocket.dto;

import br.com.pedroalano.my_pocket.enumtype.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryRequest(
        @NotNull @NotBlank String name,
        @NotNull TransactionType categoryType,
        @NotNull Long order
        ) {
}
