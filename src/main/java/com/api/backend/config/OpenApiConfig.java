package com.api.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI cimcopressOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Backend")
                        .description("API pour la gestion des voitures, des terrains et le l'immobilier")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Asban&NF")
                                .email("contact@asban&nf.com")))
                .addServersItem(new Server().url("/").description("Default Server URL"));
    }
} 