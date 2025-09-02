package com.exemplo.agendamentoServicos.DTO;

import com.exemplo.agendamentoServicos.entity.Client;
import com.exemplo.agendamentoServicos.entity.PaymentType;
import com.exemplo.agendamentoServicos.entity.Product;
import com.exemplo.agendamentoServicos.entity.TypeOfRefund;

import java.time.LocalDate;
import java.util.Optional;

public record RequestSchedulingDTO(
        Long hour,
        LocalDate date,
        Client client,
        Product product,
        int totalValue,
        PaymentType paymentType,
        TypeOfRefund typeOfRefund,
        boolean realized,
        boolean active
) {
}
