package com.exemplo.agendamentoServicos.service;

import com.exemplo.agendamentoServicos.DTO.RequestSchedulingDTO;
import com.exemplo.agendamentoServicos.DTO.ResponseSchedulingDTO;
import com.exemplo.agendamentoServicos.entity.Scheduling;
import com.exemplo.agendamentoServicos.entity.TypeOfRefund;
import com.exemplo.agendamentoServicos.repository.SchedulingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SchedulingService {

    private SchedulingRepository repository;
    private ClientService clientService;
    private ProductService productService;
    private PaymentTypeService paymentTypeService;
    private TypeOfRefundService typeOfRefundService;

    public SchedulingService(SchedulingRepository repository,
                             ClientService clientService,
                             ProductService productService,
                             PaymentTypeService paymentTypeService,
                             TypeOfRefundService typeOfRefundService){
        this.repository = repository;
        this.clientService = clientService;
        this.productService = productService;
        this.paymentTypeService = paymentTypeService;
        this.typeOfRefundService = typeOfRefundService;

    }

    public ResponseSchedulingDTO toResponseDTO(Scheduling scheduling){
        return new ResponseSchedulingDTO(
                scheduling.getId(),
                scheduling.getDate(),
                scheduling.getHour(),
                Optional.ofNullable(clientService.toResponseDTO(scheduling.getClient())),
                Optional.ofNullable(productService.toResponseDTO(scheduling.getProduct())),
                scheduling.getTotalValue(),
                Optional.ofNullable(paymentTypeService.toResponseDTO(scheduling.getPaymentType())),
                Optional.ofNullable(typeOfRefundService.toResponse(scheduling.getTypeOfRefund())),
                scheduling.isRealized(),
                scheduling.isActive()
        );
    }

    public List<ResponseSchedulingDTO> listAllScheduling(){
        return repository
                .findAll()
                .stream()
                .filter(Scheduling::isActive)
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ResponseSchedulingDTO schedulingById (Long id){
        return repository
                .findById(id)
                .filter(Scheduling::isActive)
                .map(this::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

    }

    public ResponseSchedulingDTO createScheduling (RequestSchedulingDTO dto){
        Scheduling newScheduling = new Scheduling(dto);
        repository.save(newScheduling);
        return toResponseDTO(newScheduling);
    }

    public ResponseSchedulingDTO updateScheduling (Long id, RequestSchedulingDTO dto){
        return repository.findById(id)
                .filter(Scheduling::isActive)
                .map(scheduling -> {
                    scheduling.setHour(dto.hour());
                    scheduling.setDate(dto.date());
                    scheduling.setClient(dto.client());
                    scheduling.setProduct(dto.product());
                    scheduling.setPaymentType(dto.paymentType());
                    scheduling.setTypeOfRefund(dto.typeOfRefund());
                    scheduling.setTotalValue(dto.totalValue());
                    scheduling.setRealized(dto.realized());
                    return toResponseDTO(repository.save(scheduling));
                }).orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
    }

    public void deleteScheduling (long id){
        if (!repository.existsById(id)){
            throw new RuntimeException("Agendamento não encontrado");
        }

        repository.findById(id)
                .map(scheduling -> {
                    scheduling.setActive(false);
                    return repository.save(scheduling);
                }).orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
    }
}
