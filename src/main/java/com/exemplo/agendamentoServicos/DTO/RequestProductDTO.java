package com.exemplo.agendamentoServicos.DTO;

import java.util.Optional;

public record RequestProductDTO(
        String name,
        Long price,
        Optional<Boolean> active,
        Long comission,
        Long duration
) {
}
