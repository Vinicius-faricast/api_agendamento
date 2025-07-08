package com.exemplo.agendamentoServicos.controller;

import com.exemplo.agendamentoServicos.DTO.RequestClientDTO;
import com.exemplo.agendamentoServicos.DTO.ResponseClientDTO;
import com.exemplo.agendamentoServicos.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/client")
public class ClientController {

    private ClientService service;

    public ClientController( ClientService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResponseClientDTO> createClient(@RequestBody RequestClientDTO dto){
        ResponseClientDTO clientCreate = service.createClient(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientCreate);
    }

    @GetMapping
    public ResponseEntity<List<ResponseClientDTO>> listClient(){
        List<ResponseClientDTO> listClient = service.listClient();
        return ResponseEntity.ok(listClient);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseClientDTO> clientById(@PathVariable long id){
        try{
            ResponseClientDTO client = service.listClientById(id);
            return ResponseEntity.ok(client);
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseClientDTO> clientUpdate(@PathVariable long id, @RequestBody RequestClientDTO dto){
        try {
            ResponseClientDTO updateClient = service.updateClient(id, dto);
            return ResponseEntity.ok(updateClient);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> clientDelete(@PathVariable long id){
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
