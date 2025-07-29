package com.exemplo.agendamentoServicos.controller;

import com.exemplo.agendamentoServicos.DTO.RequestPaymentTypeDTO;
import com.exemplo.agendamentoServicos.DTO.ResponseClientDTO;
import com.exemplo.agendamentoServicos.DTO.ResponsePaymentTypeDTO;
import com.exemplo.agendamentoServicos.service.PaymentTypeService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/paymentType")
public class PaymentTypeController {

    private PaymentTypeService service;

    public PaymentTypeController(PaymentTypeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResponsePaymentTypeDTO> createPaymentType(@RequestBody RequestPaymentTypeDTO body) {
        ResponsePaymentTypeDTO createPaymentType = service.createPaymentType(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(createPaymentType);
    }

    @GetMapping
    public ResponseEntity<List<ResponsePaymentTypeDTO>> listAllPaymentType() {
        List<ResponsePaymentTypeDTO> listPaymentType = service.listAllPaymentType();

        return ResponseEntity.ok(listPaymentType);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponsePaymentTypeDTO> paymentTypeById(@PathVariable long id) {
        try {
            ResponsePaymentTypeDTO paymentType = service.paymentTypeById(id);
            return ResponseEntity.ok(paymentType);
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponsePaymentTypeDTO> updatePaymentType(@PathVariable long id, @RequestBody RequestPaymentTypeDTO dto){
        try {
            ResponsePaymentTypeDTO updatePaymentType = service.updatePaymentType(id, dto);
            return ResponseEntity.ok(updatePaymentType);
        }catch (RuntimeException e){
            return  ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePaymentType(@PathVariable long id){
        try {
            service.deletePaymentType(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
