package com.exemplo.agendamentoServicos.entity;

import com.exemplo.agendamentoServicos.DTO.RequestSchedulingDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="scheduling")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Scheduling {

    public Scheduling(RequestSchedulingDTO dto){
        this.hour = dto.hour();
        this.date = dto.date();
        this.client = dto.client();
        this.product = dto.product();
        this.paymentType = null;
        this.typeOfRefund = null;
        this.totalValue = dto.totalValue();
        this.realized = false;
        this.active = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long hour;

    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "services_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_type_id")
    private PaymentType paymentType;

    private int totalValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_of_refund_id")
    private TypeOfRefund typeOfRefund;

    private boolean realized;

    private boolean active;
}
