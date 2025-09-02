package com.exemplo.agendamentoServicos.DTO;

import java.util.Optional;

public record ResponsePaymentTypeDTO(
        Long id,
        String type,
        long tax
) {
}
