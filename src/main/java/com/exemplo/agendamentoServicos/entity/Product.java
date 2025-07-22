package com.exemplo.agendamentoServicos.entity;

import com.exemplo.agendamentoServicos.DTO.RequestProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "services")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    public Product(RequestProductDTO dto){
        this.name = dto.name();
        this.price = dto.price();
        this.comission = dto.comission();
        this.duration = dto.duration();
        this.active = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private Long price;

    private boolean active;

    private Long comission;

    private Long duration;
}
