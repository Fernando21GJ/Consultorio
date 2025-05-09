package com.consultorios.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Consultorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private String piso;
    private String nombre;  // Agregado el campo 'nombre'
    private String direccion;  // Agregado el campo 'direccion'
    private String telefono;  // Agregado el campo 'telefono'

    @OneToMany(mappedBy = "consultorio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cita> citas;
}
