package com.consultorios.api.repository;

import com.consultorios.api.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    // Método para encontrar un médico por su correo electrónico
    Optional<Medico> findByEmail(String email);
}
