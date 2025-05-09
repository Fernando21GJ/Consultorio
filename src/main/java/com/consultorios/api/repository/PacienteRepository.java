package com.consultorios.api.repository;

import com.consultorios.api.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    // Método para encontrar un paciente por su correo electrónico
    Optional<Paciente> findByEmail(String email);
}
