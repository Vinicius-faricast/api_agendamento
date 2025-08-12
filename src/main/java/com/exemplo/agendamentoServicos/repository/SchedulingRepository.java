package com.exemplo.agendamentoServicos.repository;

import com.exemplo.agendamentoServicos.entity.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {
}
