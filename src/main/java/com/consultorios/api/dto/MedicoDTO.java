package com.consultorios.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MedicoDTO {

    private Long id;

    @NotBlank(message = "El nombre del médico es obligatorio")
    private String nombre;

    @Email(message = "Correo electrónico inválido")
    private String email;

    @Size(min = 10, max = 15, message = "El número telefónico debe tener entre 10 y 15 caracteres")
    private String telefono;

    @NotBlank(message = "La especialidad es obligatoria")
    private String especilidad;
}
