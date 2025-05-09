package com.consultorios.api.controller;

import com.consultorios.api.dto.MedicoDTO;
import com.consultorios.api.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    // Crear nuevo médico
    @PostMapping
    public ResponseEntity<MedicoDTO> createMedico(@RequestBody MedicoDTO medicoDTO) {
        MedicoDTO savedMedico = medicoService.save(medicoDTO);
        return new ResponseEntity<>(savedMedico, HttpStatus.CREATED);
    }

    // Obtener todos los médicos
    @GetMapping
    public ResponseEntity<List<MedicoDTO>> getAllMedicos() {
        List<MedicoDTO> medicos = medicoService.findAll();
        return new ResponseEntity<>(medicos, HttpStatus.OK);
    }

    // Obtener médico por ID
    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> getMedicoById(@PathVariable Long id) {
        MedicoDTO medico = medicoService.findById(id);
        return medico != null
                ? new ResponseEntity<>(medico, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Actualizar médico
    @PutMapping("/{id}")
    public ResponseEntity<MedicoDTO> updateMedico(@PathVariable Long id, @RequestBody MedicoDTO medicoDTO) {
        MedicoDTO updatedMedico = medicoService.update(id, medicoDTO);
        return updatedMedico != null
                ? new ResponseEntity<>(updatedMedico, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Eliminar médico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedico(@PathVariable Long id) {
        boolean isDeleted = medicoService.delete(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
