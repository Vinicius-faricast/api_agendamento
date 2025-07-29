package com.exemplo.agendamentoServicos.entity;

import com.exemplo.agendamentoServicos.DTO.RequestPaymentTypeDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private long id;

    private String type;

    private long tax;

    private boolean active;
}
