package com.exemplo.agendamentoServicos.controller;

import com.exemplo.agendamentoServicos.DTO.RequestTypeOfRefundDTO;
import com.exemplo.agendamentoServicos.DTO.ResponseTypeOfRefundDTO;
import com.exemplo.agendamentoServicos.service.TypeOfRefundService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/typeofrefund")
public class TypeOfRefundController {

    private TypeOfRefundService service;

    public TypeOfRefundController(TypeOfRefundService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResponseTypeOfRefundDTO> createTypeOfRefund(@RequestBody @Valid RequestTypeOfRefundDTO body) {
        ResponseTypeOfRefundDTO response = service.createTypeOfRefund(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ResponseTypeOfRefundDTO>> listAllTypeOfRefund() {
        List<ResponseTypeOfRefundDTO> listAllResponse = service.findAllTypeOfRefund();
        return ResponseEntity.ok(listAllResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseTypeOfRefundDTO> findTypeOfRefundById(@PathVariable int id) {
        ResponseTypeOfRefundDTO typeOfRefundDTO = service.typeOfRefundById(id);

        try {
            return ResponseEntity.ok(typeOfRefundDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseTypeOfRefundDTO> updateTypeOfRefund(@PathVariable int id, @RequestBody RequestTypeOfRefundDTO body) {

        ResponseTypeOfRefundDTO typeOfRefundDTO = service.updateTypeOfRefund(id, body);
        try {
            return ResponseEntity.ok(typeOfRefundDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTypeOfRefund(@PathVariable int id) {
        try {
            service.deleteTypeOfRefund(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
