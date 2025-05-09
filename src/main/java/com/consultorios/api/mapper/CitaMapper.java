package com.consultorios.api.mapper;

import com.consultorios.api.dto.CitaDTO;
import com.consultorios.api.model.Cita;
import com.consultorios.api.model.Medico;
import com.consultorios.api.model.Paciente;
import com.consultorios.api.model.Consultorio;

public class CitaMapper {

    // Convierte la entidad Cita a CitaDTO
    public static CitaDTO toDTO(Cita cita) {
        return CitaDTO.builder()
                .id(cita.getId())
                .fechaHora(cita.getFechaHora())
                .motivo(cita.getMotivo())
                .medicoId(cita.getMedico().getId())
                .pacienteId(cita.getPaciente().getId())
                .consultorioId(cita.getConsultorio() != null ? cita.getConsultorio().getId() : null) // Obtener el ID del Consultorio
                .build();
    }

    // Convierte el CitaDTO a la entidad Cita
    public static Cita toEntity(CitaDTO dto, Medico medico, Paciente paciente, Consultorio consultorio) {
        return Cita.builder()
                .id(dto.getId())
                .fechaHora(dto.getFechaHora())
                .motivo(dto.getMotivo())
                .medico(medico)
                .paciente(paciente)
                .consultorio(consultorio) // Asignar el Consultorio
                .build();
    }
}
