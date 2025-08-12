package com.exemplo.agendamentoServicos.service;

import com.exemplo.agendamentoServicos.DTO.ResponseSchedulingDTO;
import com.exemplo.agendamentoServicos.entity.Scheduling;
import com.exemplo.agendamentoServicos.repository.SchedulingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchedulingService {

    private SchedulingRepository repository;

    public SchedulingService(SchedulingRepository repository){
        this.repository = repository;
    }

    public ResponseSchedulingDTO toResponseDTO(Scheduling scheduling){
        return new ResponseSchedulingDTO(
                scheduling.getId(),
                scheduling.getDate(),
                scheduling.getHour(),
                scheduling.getClient(),
                scheduling.getProduct(),
                scheduling.getTotalValue(),
                scheduling.getPaymentType(),
                scheduling.getTypeOfRefund(),
                scheduling.isRealized()
        );
    }

    public List<ResponseSchedulingDTO> listAllScheduling(){
        return repository
                .findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
