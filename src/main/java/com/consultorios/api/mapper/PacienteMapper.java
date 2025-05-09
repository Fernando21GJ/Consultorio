package com.consultorios.api.mapper;

import com.consultorios.api.dto.PacienteDTO;
import com.consultorios.api.model.Paciente;

public class PacienteMapper {

    public static PacienteDTO toDTO(Paciente paciente) {
        return PacienteDTO.builder()
                .id(paciente.getId())
                .nombre(paciente.getNombre())
                .email(paciente.getEmail())
                .telefono(paciente.getTelefono())
                .build();
    }

    public static Paciente toEntity(PacienteDTO dto) {
        return Paciente.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .email(dto.getEmail())
                .telefono(dto.getTelefono())
                .build();
    }
}
