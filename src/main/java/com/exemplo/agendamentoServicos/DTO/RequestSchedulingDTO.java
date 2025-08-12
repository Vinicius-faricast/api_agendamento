package com.exemplo.agendamentoServicos.DTO;

import com.exemplo.agendamentoServicos.entity.Client;
import com.exemplo.agendamentoServicos.entity.PaymentType;
import com.exemplo.agendamentoServicos.entity.Product;
import com.exemplo.agendamentoServicos.entity.TypeOfRefund;

import java.time.LocalDate;

public record RequestSchedulingDTO(
        Long hour,
        LocalDate date,
        Client client,
        Product product,
        PaymentType paymentType,
        TypeOfRefund typeOfRefund,
        Boolean realized
) {
}
