package com.exemplo.agendamentoServicos.entity;

import com.exemplo.agendamentoServicos.DTO.RequestTypeOfRefundDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "type_of_refund")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeOfRefund {

    public TypeOfRefund (RequestTypeOfRefundDTO dto){
        this.description = dto.description();
        this.active = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private boolean active;
}
