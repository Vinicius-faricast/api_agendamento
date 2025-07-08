package com.exemplo.agendamentoServicos.DTO;

import java.util.Optional;

public record RequestClientDTO(
        String name,
        Long overbalance,
        Optional<Boolean> active
) {
}
