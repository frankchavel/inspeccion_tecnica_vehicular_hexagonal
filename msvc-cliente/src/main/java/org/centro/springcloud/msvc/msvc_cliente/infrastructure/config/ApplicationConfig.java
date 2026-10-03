package org.centro.springcloud.msvc.msvc_cliente.infrastructure.config;

import org.centro.springcloud.msvc.msvc_cliente.application.service.ClienteService;
import org.centro.springcloud.msvc.msvc_cliente.domain.port.out.ClienteRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public ClienteService clienteService(ClienteRepositoryPort repository) {
        return new ClienteService(repository);
    }
}
