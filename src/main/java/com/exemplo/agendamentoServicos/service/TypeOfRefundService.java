package com.exemplo.agendamentoServicos.service;

import com.exemplo.agendamentoServicos.DTO.RequestTypeOfRefundDTO;
import com.exemplo.agendamentoServicos.DTO.ResponseTypeOfRefundDTO;
import com.exemplo.agendamentoServicos.entity.TypeOfRefund;
import com.exemplo.agendamentoServicos.repository.TypeOfRefundRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeOfRefundService {

    private TypeOfRefundRepository repository;

    public TypeOfRefundService(TypeOfRefundRepository repository){
        this.repository = repository;
    }

    public ResponseTypeOfRefundDTO toResponse(TypeOfRefund typeOfRefund){
        if(typeOfRefund != null){
            return new ResponseTypeOfRefundDTO(
                    typeOfRefund.getId(),
                    typeOfRefund.getDescription()
            );
        }
        return null;
    }

    public List<ResponseTypeOfRefundDTO> findAllTypeOfRefund(){
        return repository
                .findAll()
                .stream()
                .filter(TypeOfRefund::isActive)
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ResponseTypeOfRefundDTO typeOfRefundById(int id){
        return repository
                .findById(id)
                .filter(TypeOfRefund::isActive)
                .map(this::toResponse)
                .orElseThrow(()-> new RuntimeException("tipo de estorno não econtrado"));
    }

    public ResponseTypeOfRefundDTO createTypeOfRefund(RequestTypeOfRefundDTO dto){
        TypeOfRefund typeOfRefund = new TypeOfRefund(dto);

        repository.save(typeOfRefund);
        return toResponse(typeOfRefund);
    }

    public ResponseTypeOfRefundDTO updateTypeOfRefund(int id, RequestTypeOfRefundDTO dto){

        return repository
                .findById(id)
                .filter(TypeOfRefund::isActive)
                .map(typeOfRefund -> {
                    typeOfRefund.setDescription(dto.description());
                    return toResponse(repository.save(typeOfRefund));
                }).orElseThrow(() -> new RuntimeException("Tipo de estorno não encontrado"));
    }

    public void deleteTypeOfRefund(int id){

        if(!repository.existsById(id)){
            throw new RuntimeException("Tipo de estorno não encontrado!");
        }

        repository.findById(id)
                .filter(TypeOfRefund::isActive)
                .map(typeOfRefund -> {
                    typeOfRefund.setActive(false);
                    return repository.save(typeOfRefund);
                })
                .orElseThrow(() -> new RuntimeException("Tipo de estorno não econtrado"));
    }
}
