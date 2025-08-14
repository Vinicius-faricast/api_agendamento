package com.exemplo.agendamentoServicos.controller;

import com.exemplo.agendamentoServicos.DTO.RequestSchedulingDTO;
import com.exemplo.agendamentoServicos.DTO.ResponseSchedulingDTO;
import com.exemplo.agendamentoServicos.service.SchedulingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/agendamento")
public class SchedulingController {

    private SchedulingService service;

    public SchedulingController (SchedulingService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResponseSchedulingDTO> createScheduling(@RequestBody RequestSchedulingDTO dto){
        ResponseSchedulingDTO response = service.createScheduling(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ResponseSchedulingDTO>> listAllScheduling(){
        List<ResponseSchedulingDTO> allScheduling = service.listAllScheduling();

        return ResponseEntity.ok(allScheduling);
    }


}
