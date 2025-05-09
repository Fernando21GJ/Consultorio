package com.consultorios.api.repository;

import com.consultorios.api.model.Consultorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsultorioRepository extends JpaRepository<Consultorio, Long> {
    // Puedes agregar métodos personalizados aquí si lo necesitas.
    // Ejemplo: Buscar consultorio por nombre
    Optional<Consultorio> findByNombre(String nombre);
}
