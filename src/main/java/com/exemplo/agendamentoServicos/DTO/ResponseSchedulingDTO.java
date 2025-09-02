package com.exemplo.agendamentoServicos.DTO;

import com.exemplo.agendamentoServicos.entity.Client;
import com.exemplo.agendamentoServicos.entity.PaymentType;
import com.exemplo.agendamentoServicos.entity.Product;
import com.exemplo.agendamentoServicos.entity.TypeOfRefund;

import java.time.LocalDate;
import java.util.Optional;

public record ResponseSchedulingDTO(
        Long id,
        LocalDate date,
        Long hour,
        Optional<ResponseClientDTO> client,
        Optional<ResponseProductDTO> product,
        int totalValue,
        Optional<ResponsePaymentTypeDTO> paymentType,
        Optional<ResponseTypeOfRefundDTO> typeOfRefund,
        boolean realized,
        boolean active
){
}
