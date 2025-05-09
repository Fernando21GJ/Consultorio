package com.consultorios.api.mongo;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "citas_cache")
public class CitaCache {
    @Id
    private String id;

    private Long citaId;

    @Field("fecha_hora")  // Puedes usar esta anotación para controlar cómo se guarda
    private LocalDateTime fechaHora;

    private String motivo;
}
