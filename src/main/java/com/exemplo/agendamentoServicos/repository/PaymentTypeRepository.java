package com.exemplo.agendamentoServicos.repository;

import com.exemplo.agendamentoServicos.entity.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {
}
