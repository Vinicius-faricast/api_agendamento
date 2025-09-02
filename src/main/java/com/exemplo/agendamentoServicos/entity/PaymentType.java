package com.exemplo.agendamentoServicos.entity;

import com.exemplo.agendamentoServicos.DTO.RequestPaymentTypeDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Entity
@Table(name = "payment_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentType {

    public PaymentType(RequestPaymentTypeDTO dto){
        this.type = dto.type();
        this.tax = dto.tax();
        this.active = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private int tax;

    private boolean active;
}
