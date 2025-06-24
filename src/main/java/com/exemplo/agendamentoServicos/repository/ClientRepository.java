package com.exemplo.agendamentoServicos.repository;

import com.exemplo.agendamentoServicos.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
