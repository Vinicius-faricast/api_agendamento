package com.exemplo.agendamentoServicos.service;

import com.exemplo.agendamentoServicos.DTO.RequestClientDTO;
import com.exemplo.agendamentoServicos.DTO.RequestPaymentTypeDTO;
import com.exemplo.agendamentoServicos.DTO.ResponsePaymentTypeDTO;
import com.exemplo.agendamentoServicos.entity.PaymentType;
import com.exemplo.agendamentoServicos.repository.PaymentTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentTypeService {

    private PaymentTypeRepository repository;

    public PaymentTypeService(PaymentTypeRepository repository){
        this.repository = repository;
    }

    public ResponsePaymentTypeDTO toResponseDTO(PaymentType paymentType){
        return new ResponsePaymentTypeDTO(
                paymentType.getId(),
                paymentType.getType(),
                paymentType.getTax()
        );
    }

    public List<ResponsePaymentTypeDTO> listAllPaymentType(){
        return repository
                .findAll()
                .stream()
                .filter(PaymentType::isActive)
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ResponsePaymentTypeDTO paymentTypeById(long id){
        return repository
                .findById(id)
                .filter(PaymentType::isActive)
                .map(this::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Tipo de pagamento não encontrado"));
    }

    public ResponsePaymentTypeDTO createPaymentType(RequestPaymentTypeDTO dto){
        PaymentType paymentType = new PaymentType(dto);

        repository.save(paymentType);
        return toResponseDTO(paymentType);
    }

    public ResponsePaymentTypeDTO updatePaymentType(long id, RequestPaymentTypeDTO dto){
        return repository.findById(id)
                .filter(PaymentType::isActive)
                .map(paymentType -> {
                    paymentType.setType(dto.type());
                    paymentType.setTax(dto.tax());
                    return toResponseDTO(repository.save(paymentType));
                })
                .orElseThrow(() -> new RuntimeException("Tipo de pagamento não encontrado"));
    }

    public void deletePaymentType(long id){

        if(!repository.existsById(id)){
            throw new RuntimeException("Tipo de pagamento não encontrado!");
        }

        repository.findById(id)
                .map(paymentType -> {
                    paymentType.setActive(false);
                    return  repository.save(paymentType);
                })
                .orElseThrow(() -> new RuntimeException("Tipo de pagamento não encontrado"));
    }
}
