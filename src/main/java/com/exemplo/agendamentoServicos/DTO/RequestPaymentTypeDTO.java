package com.exemplo.agendamentoServicos.DTO;

import java.util.Optional;

public record RequestPaymentTypeDTO(
        String type,
        long tax,
        Optional<Boolean> active
) {
}
