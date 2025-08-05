package com.exemplo.agendamentoServicos.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public record RequestTypeOfRefundDTO(
        @NotNull
        @NotBlank
        String description,
        Optional<Boolean> active
) {
}
