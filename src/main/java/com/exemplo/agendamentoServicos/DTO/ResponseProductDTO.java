package com.exemplo.agendamentoServicos.DTO;

public record ResponseProductDTO(
        Long id,
        String name,
        Long price,
        Long comission,
        Long duration) {
}
