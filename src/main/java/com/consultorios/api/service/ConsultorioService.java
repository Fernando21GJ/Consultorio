package com.consultorios.api.service;

import com.consultorios.api.dto.ConsultorioDTO;
import com.consultorios.api.mapper.ConsultorioMapper;
import com.consultorios.api.model.Consultorio;
import com.consultorios.api.repository.ConsultorioRepository;
import com.consultorios.api.exception.ResourceNotFoundException; // Excepción personalizada
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultorioService {

    @Autowired
    private ConsultorioRepository consultorioRepository;

    // Método para guardar un nuevo Consultorio
    public ConsultorioDTO save(ConsultorioDTO dto) {
        // Convertimos el DTO en una entidad Consultorio y lo guardamos en la base de datos
        Consultorio consultorio = ConsultorioMapper.toEntity(dto);
        Consultorio savedConsultorio = consultorioRepository.save(consultorio);
        // Retornamos el DTO del Consultorio guardado
        return ConsultorioMapper.toDTO(savedConsultorio);
    }

    // Método para obtener todos los Consultorios
    public List<ConsultorioDTO> findAll() {
        return consultorioRepository.findAll().stream()
                .map(ConsultorioMapper::toDTO)
                .toList();
    }

    // Método para encontrar un Consultorio por su ID
    public ConsultorioDTO findById(Long id) {
        // Buscamos el consultorio por su ID, si no existe lanzamos una excepción
        Consultorio consultorio = consultorioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultorio no encontrado con id: " + id));
        return ConsultorioMapper.toDTO(consultorio);
    }

    // Método para actualizar un Consultorio existente
    public ConsultorioDTO update(Long id, ConsultorioDTO dto) {
        // Buscamos el consultorio que se desea actualizar
        Consultorio consultorio = consultorioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultorio no encontrado con id: " + id));

        // Actualizamos los campos del consultorio si es necesario
        boolean updated = false;
        if (!consultorio.getNombre().equals(dto.getNombre())) {
            consultorio.setNombre(dto.getNombre());
            updated = true;
        }
        if (!consultorio.getDireccion().equals(dto.getDireccion())) {
            consultorio.setDireccion(dto.getDireccion());
            updated = true;
        }
        if (!consultorio.getTelefono().equals(dto.getTelefono())) {
            consultorio.setTelefono(dto.getTelefono());
            updated = true;
        }

        // Si se han realizado cambios, lo guardamos
        if (updated) {
            Consultorio updatedConsultorio = consultorioRepository.save(consultorio);
            return ConsultorioMapper.toDTO(updatedConsultorio);
        }

        // Si no se ha realizado ningún cambio, devolvemos el DTO sin modificar
        return ConsultorioMapper.toDTO(consultorio);
    }

    // Método para eliminar un Consultorio por su ID
    public boolean delete(Long id) {
        // Verificamos si el consultorio existe antes de intentar eliminarlo
        if (consultorioRepository.existsById(id)) {
            consultorioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
