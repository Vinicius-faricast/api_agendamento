package com.exemplo.agendamentoServicos.service;

import com.exemplo.agendamentoServicos.DTO.RequestClientDTO;
import com.exemplo.agendamentoServicos.DTO.ResponseClientDTO;
import com.exemplo.agendamentoServicos.entity.Client;
import com.exemplo.agendamentoServicos.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.Collectors;

@Service
public class ClientService {

    private ClientRepository repository;

    public ClientService(ClientRepository repository){
        this.repository = repository;
    }

    public ResponseClientDTO toResponseDTO(Client client){
        return new ResponseClientDTO(
                client.getId(),
                client.getName(),
                client.getOverbalance()
        );
    }

    public List<ResponseClientDTO> listClient(){
        return repository
                .findAll()
                .stream()
                .filter(Client::isActive)
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ResponseClientDTO listClientById(Long id){
        return repository.findById(id)
                .filter(Client::isActive)
                .map(this::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public ResponseClientDTO createClient(RequestClientDTO dto){
        Client newClient = new Client(dto.name(), dto.overbalance());
        repository.save(newClient);
        return toResponseDTO(newClient);
    }

    public ResponseClientDTO updateClient(Long id, RequestClientDTO dto){
        return repository.findById(id)
                .filter(Client::isActive)
                .map(client -> {
            client.setName(dto.name());
            client.setOverbalance(dto.overbalance());
            return toResponseDTO(repository.save(client));
        }).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public void delete(Long id){

        if(!repository.existsById(id)){
            throw new RuntimeException("Cliente não econtrado");
        }

        repository.findById(id).map(client -> {
           client.setActive(false);
           return repository.save(client);
        }).orElseThrow(() -> new RuntimeException("Cliente não econtrado"));

    }

}
