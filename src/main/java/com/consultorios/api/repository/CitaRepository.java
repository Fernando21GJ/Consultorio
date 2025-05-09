package com.consultorios.api.repository;

import com.consultorios.api.model.Cita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    List<Cita> findByMedicoId(Long medicoId);

    List<Cita> findByPacienteId(Long pacienteId);

    @Query("SELECT c FROM Cita c JOIN FETCH c.paciente p WHERE c.medico.id = :medicoId")
    List<Cita> findCitasConPacienteByMedicoId(Long medicoId);

    // Método con paginación
    Page<Cita> findByMedicoId(Long medicoId, Pageable pageable);

    // Método con ordenación
    List<Cita> findByMedicoIdOrderByFechaHoraAsc(Long medicoId);
}
