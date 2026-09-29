package org.centro.msvc_vehiculo.infrastructure.config;

import org.centro.msvc_vehiculo.application.service.VehiculoService;
import org.centro.msvc_vehiculo.domain.port.out.VehiculoRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de Beans de la capa de aplicación.
 * Instancia y expone VehiculoService inyectando el puerto de salida VehiculoRepositoryPort.
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public VehiculoService vehiculoService(VehiculoRepositoryPort vehiculoRepositoryPort) {
        return new VehiculoService(vehiculoRepositoryPort);
    }
}
