package com.consultorios.api.service;

import com.consultorios.api.dto.PacienteDTO;
import com.consultorios.api.exception.ResourceNotFoundException;
import com.consultorios.api.mapper.PacienteMapper;
import com.consultorios.api.model.Paciente;
import com.consultorios.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public PacienteDTO save(PacienteDTO dto) {
        Paciente entity = PacienteMapper.toEntity(dto);
        Paciente saved = repository.save(entity);
        return PacienteMapper.toDTO(saved);
    }

    public List<PacienteDTO> findAll() {
        return repository.findAll().stream()
                .map(PacienteMapper::toDTO)
                .toList();
    }

    public PacienteDTO findById(Long id) {
        return repository.findById(id)
                .map(PacienteMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con id: " + id));
    }

    public PacienteDTO update(Long id, PacienteDTO dto) {
        Paciente existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con id: " + id));

        existing.setNombre(dto.getNombre());
        existing.setEmail(dto.getEmail());
        existing.setTelefono(dto.getTelefono());

        return PacienteMapper.toDTO(repository.save(existing));
    }

    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        throw new ResourceNotFoundException("Paciente no encontrado con id: " + id);
    }
}
