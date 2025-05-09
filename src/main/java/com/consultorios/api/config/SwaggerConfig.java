package com.consultorios.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springdoc.core.models.GroupedOpenApi;


@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Consultorios API",
                version = "1.0",
                description = "API para la gestión de citas médicas, médicos y pacientes."
        )
)
public class SwaggerConfig {

    /**
     * Configuración básica de Swagger.
     */
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")  // Indica que todas las rutas se incluirán en este grupo
                .build();
    }


}
