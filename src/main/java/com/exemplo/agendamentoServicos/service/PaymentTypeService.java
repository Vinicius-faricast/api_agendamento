package com.exemplo.agendamentoServicos.service;

import com.exemplo.agendamentoServicos.DTO.ResponsePaymentTypeDTO;
import com.exemplo.agendamentoServicos.entity.PaymentType;
import com.exemplo.agendamentoServicos.repository.PaymentTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentTypeService {

    private PaymentTypeRepository repository;

    public PaymentTypeService(PaymentTypeRepository repository){
        this.repository = repository;
    }

    public ResponsePaymentTypeDTO toResponseDTO(PaymentType paymentType){
        return new ResponsePaymentTypeDTO(paymentType.getId(), paymentType.getType(), paymentType.getTax());
    }
}
