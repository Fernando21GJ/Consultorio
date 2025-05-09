package com.consultorios.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PacienteDTO {

    private Long id;

    @NotBlank(message = "El nombre del paciente es obligatorio")
    private String nombre;

    @Email(message = "Correo electrónico inválido")
    private String email;

    @Size(min = 10, max = 15, message = "El número telefónico debe tener entre 10 y 15 caracteres")
    private String telefono;
}
