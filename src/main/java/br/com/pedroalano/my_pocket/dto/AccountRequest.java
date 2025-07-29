package br.com.pedroalano.my_pocket.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AccountRequest(
        @NotNull @NotBlank String name
) {
}
