package com.consultorios.api.controller;



import com.consultorios.api.dto.ConsultorioDTO;
import com.consultorios.api.service.ConsultorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consultorios")
public class ConsultorioController {

    @Autowired
    private ConsultorioService consultorioService;

    // Crear nuevo consultorio
    @PostMapping
    public ResponseEntity<ConsultorioDTO> createConsultorio(@RequestBody ConsultorioDTO consultorioDTO) {
        ConsultorioDTO savedConsultorio = consultorioService.save(consultorioDTO);
        return new ResponseEntity<>(savedConsultorio, HttpStatus.CREATED);
    }

    // Obtener todos los consultorios
    @GetMapping
    public ResponseEntity<List<ConsultorioDTO>> getAllConsultorios() {
        List<ConsultorioDTO> consultorios = consultorioService.findAll();
        return new ResponseEntity<>(consultorios, HttpStatus.OK);
    }

    // Obtener consultorio por ID
    @GetMapping("/{id}")
    public ResponseEntity<ConsultorioDTO> getConsultorioById(@PathVariable Long id) {
        ConsultorioDTO consultorio = consultorioService.findById(id);
        return consultorio != null
                ? new ResponseEntity<>(consultorio, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Actualizar consultorio
    @PutMapping("/{id}")
    public ResponseEntity<ConsultorioDTO> updateConsultorio(@PathVariable Long id, @RequestBody ConsultorioDTO consultorioDTO) {
        ConsultorioDTO updatedConsultorio = consultorioService.update(id, consultorioDTO);
        return updatedConsultorio != null
                ? new ResponseEntity<>(updatedConsultorio, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // Eliminar consultorio
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultorio(@PathVariable Long id) {
        boolean isDeleted = consultorioService.delete(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
