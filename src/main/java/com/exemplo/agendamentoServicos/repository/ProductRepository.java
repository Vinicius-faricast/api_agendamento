package com.exemplo.agendamentoServicos.repository;

import com.exemplo.agendamentoServicos.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
