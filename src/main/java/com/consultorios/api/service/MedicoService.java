package com.consultorios.api.service;

import com.consultorios.api.dto.MedicoDTO;
import com.consultorios.api.mapper.MedicoMapper;
import com.consultorios.api.model.Medico;
import com.consultorios.api.repository.MedicoRepository;
import com.consultorios.api.exception.ResourceNotFoundException; // Importa la excepción
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    // Método para guardar un nuevo médico
    public MedicoDTO save(MedicoDTO dto) {
        Medico entity = MedicoMapper.toEntity(dto);
        Medico saved = repository.save(entity);
        return MedicoMapper.toDTO(saved);
    }

    // Método para obtener todos los médicos
    public List<MedicoDTO> findAll() {
        return repository.findAll().stream()
                .map(MedicoMapper::toDTO)
                .toList();
    }

    // Método para encontrar un médico por su ID
    public MedicoDTO findById(Long id) {
        return repository.findById(id)
                .map(MedicoMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado con id: " + id));
    }

    // Método para actualizar un médico existente
    public MedicoDTO update(Long id, MedicoDTO dto) {
        Medico existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado con id: " + id));

        // Actualizar los campos del médico con los datos del DTO
        existing.setNombre(dto.getNombre());
        existing.setEmail(dto.getEmail());
        existing.setTelefono(dto.getTelefono());
        existing.setEspecilidad(dto.getEspecilidad());

        // Guardar y mapear el médico actualizado
        Medico updated = repository.save(existing);
        return MedicoMapper.toDTO(updated);
    }

    // Método para eliminar un médico por su ID
    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        throw new ResourceNotFoundException("Médico no encontrado con id: " + id); // Lanza una excepción si no existe
    }
}
