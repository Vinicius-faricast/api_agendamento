package com.exemplo.agendamentoServicos.DTO;

public record RequestClientDTO(
        String name,
        Long overbalance,
        boolean active
) {
}
