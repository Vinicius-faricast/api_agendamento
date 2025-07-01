package com.exemplo.agendamentoServicos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Clients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    public Client(String name, Long overbalance){
        this.name = name;
        this.overbalance= overbalance;
        this.active = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long overbalance;

    private boolean active;
}
