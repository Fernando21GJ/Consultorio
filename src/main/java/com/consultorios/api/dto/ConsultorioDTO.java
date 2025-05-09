package com.consultorios.api.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultorioDTO {

    private Long id;
    private String numero;
    private String piso;
    private String nombre;  // Agregado el campo 'nombre'
    private String direccion;  // Agregado el campo 'direccion'
    private String telefono;  // Agregado el campo 'telefono'
    private List<Long> citasIds;  // Asumiendo que quieres solo los IDs de las citas

}
