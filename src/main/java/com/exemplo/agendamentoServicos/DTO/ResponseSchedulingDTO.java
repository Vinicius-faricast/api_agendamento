package com.exemplo.agendamentoServicos.DTO;

import com.exemplo.agendamentoServicos.entity.Client;
import com.exemplo.agendamentoServicos.entity.PaymentType;
import com.exemplo.agendamentoServicos.entity.Product;
import com.exemplo.agendamentoServicos.entity.TypeOfRefund;

import java.time.LocalDate;

public record ResponseSchedulingDTO(
        Long id,
        LocalDate date,
        Long hour,
        Client client,
        Product product,
        int totalValue,
        PaymentType paymentType,
        TypeOfRefund typeOfRefund,
        boolean realized,
        boolean active
){
}
