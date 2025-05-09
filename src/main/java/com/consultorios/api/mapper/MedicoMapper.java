package com.consultorios.api.mapper;

import com.consultorios.api.dto.MedicoDTO;
import com.consultorios.api.model.Medico;

public class MedicoMapper {

    public static MedicoDTO toDTO(Medico medico) {
        return MedicoDTO.builder()
                .id(medico.getId())
                .nombre(medico.getNombre())
                .email(medico.getEmail())
                .telefono(medico.getTelefono())
                .especilidad(medico.getEspecilidad())
                .build();
    }

    public static Medico toEntity(MedicoDTO dto) {
        return Medico.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .email(dto.getEmail())
                .telefono(dto.getTelefono())
                .especilidad(dto.getEspecilidad())
                .build();
    }
}
