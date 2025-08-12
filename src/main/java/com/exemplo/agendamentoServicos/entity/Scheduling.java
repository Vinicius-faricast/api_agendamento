package com.exemplo.agendamentoServicos.entity;

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
}
