package com.consultorios.api.service;

import com.consultorios.api.dto.CitaDTO;
import com.consultorios.api.mapper.CitaMapper;
import com.consultorios.api.model.Cita;
import com.consultorios.api.model.Medico;
import com.consultorios.api.model.Paciente;
import com.consultorios.api.model.Consultorio;
import com.consultorios.api.mongo.CitaCache;
import com.consultorios.api.mongo.CitaCacheRepository;
import com.consultorios.api.repository.CitaRepository;
import com.consultorios.api.repository.MedicoRepository;
import com.consultorios.api.repository.PacienteRepository;
import com.consultorios.api.repository.ConsultorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    @Autowired private CitaRepository repository;
    @Autowired private CitaCacheRepository cacheRepository;
    @Autowired private MedicoRepository medicoRepository;
    @Autowired private PacienteRepository pacienteRepository;
    @Autowired private ConsultorioRepository consultorioRepository;

    // Método para guardar una nueva cita
    public CitaDTO save(CitaDTO dto) {
        // Validar la existencia de Médico, Paciente y Consultorio
        Medico medico = medicoRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        Consultorio consultorio = consultorioRepository.findById(dto.getConsultorioId())
                .orElseThrow(() -> new RuntimeException("Consultorio no encontrado"));

        // Crear la entidad Cita desde el DTO y guardar en el repositorio
        Cita cita = repository.save(CitaMapper.toEntity(dto, medico, paciente, consultorio));

        // Guardar en la caché de MongoDB
        CitaCache cache = new CitaCache(null, cita.getId(), cita.getFechaHora(), cita.getMotivo());
        cacheRepository.save(cache);

        // Retornar el DTO de la cita guardada
        return CitaMapper.toDTO(cita);
    }

    // Método para obtener todas las citas
    public List<CitaDTO> findAll() {
        return repository.findAll().stream()
                .map(CitaMapper::toDTO)
                .toList();
    }

    // Método para buscar una cita por ID
    public Optional<CitaDTO> findById(Long id) {
        // Buscar primero en la caché
        Optional<CitaCache> cache = cacheRepository.findByCitaId(id);
        if (cache.isPresent()) {
            CitaDTO dto = new CitaDTO();
            dto.setId(id);
            dto.setFechaHora(cache.get().getFechaHora());
            dto.setMotivo(cache.get().getMotivo());
            return Optional.of(dto);
        }

        // Si no está en caché, buscar en el repositorio
        return repository.findById(id).map(CitaMapper::toDTO);
    }

    // Método para actualizar una cita existente
    public Optional<CitaDTO> update(Long id, CitaDTO dto) {
        return repository.findById(id).map(existing -> {
            // Buscar los objetos completos desde sus IDs
            Medico medico = medicoRepository.findById(dto.getMedicoId())
                    .orElseThrow(() -> new RuntimeException("Médico no encontrado"));
            Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                    .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
            Consultorio consultorio = consultorioRepository.findById(dto.getConsultorioId())
                    .orElseThrow(() -> new RuntimeException("Consultorio no encontrado"));

            // Actualizar los campos de la cita existente
            existing.setFechaHora(dto.getFechaHora());
            existing.setMotivo(dto.getMotivo());
            existing.setMedico(medico);
            existing.setPaciente(paciente);
            existing.setConsultorio(consultorio);

            // Guardar la cita actualizada en el repositorio
            Cita updated = repository.save(existing);

            // Actualizar la caché con los nuevos datos
            cacheRepository.findByCitaId(id).ifPresent(cache -> {
                cache.setFechaHora(updated.getFechaHora());
                cache.setMotivo(updated.getMotivo());
                cacheRepository.save(cache);
            });

            // Retornar el DTO actualizado
            return CitaMapper.toDTO(updated);
        });
    }

    // Método para eliminar una cita
    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            // Eliminar la cita del repositorio
            repository.deleteById(id);
            // Eliminar la cita de la caché
            cacheRepository.findByCitaId(id).ifPresent(cache -> cacheRepository.deleteById(cache.getId()));
            return true;
        }
        return false;
    }
}
