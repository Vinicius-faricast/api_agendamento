package com.exemplo.agendamentoServicos.DTO;

public record ResponsePaymentTypeDTO(
        long id,
        String type,
        long tax
) {
}
