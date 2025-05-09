package com.consultorios.api.mapper;

import com.consultorios.api.dto.ConsultorioDTO;
import com.consultorios.api.model.Consultorio;

public class ConsultorioMapper {

    // Convierte la entidad Consultorio a ConsultorioDTO
    public static ConsultorioDTO toDTO(Consultorio consultorio) {
        return ConsultorioDTO.builder()
                .id(consultorio.getId())
                .numero(consultorio.getNumero())
                .piso(consultorio.getPiso())
                .nombre(consultorio.getNombre())  // Agregado el campo 'nombre'
                .direccion(consultorio.getDireccion())  // Agregado el campo 'direccion'
                .telefono(consultorio.getTelefono())  // Agregado el campo 'telefono'
                .build();
    }

    // Convierte el ConsultorioDTO a la entidad Consultorio
    public static Consultorio toEntity(ConsultorioDTO dto) {
        return Consultorio.builder()
                .id(dto.getId())
                .numero(dto.getNumero())
                .piso(dto.getPiso())
                .nombre(dto.getNombre())  // Agregado el campo 'nombre'
                .direccion(dto.getDireccion())  // Agregado el campo 'direccion'
                .telefono(dto.getTelefono())  // Agregado el campo 'telefono'
                .build();
    }
}
