package com.Project.HealthConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Project.HealthConnect.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    boolean existsByCpf(String cpf);
}