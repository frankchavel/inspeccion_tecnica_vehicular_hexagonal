package org.centro.springcloud.msvc_ordeninspeccion.infrastructure.config;

import org.centro.springcloud.msvc_ordeninspeccion.infrastructure.adapters.ClienteClientAdapter;
import org.centro.springcloud.msvc_ordeninspeccion.infrastructure.adapters.OrdenInspeccionJpaAdapter;
import org.centro.springcloud.msvc_ordeninspeccion.infrastructure.adapters.VehiculoClientAdapter;
import org.centro.springcloud.msvc_ordeninspeccion.infrastructure.clients.ClienteFeignClient;
import org.centro.springcloud.msvc_ordeninspeccion.infrastructure.clients.VehiculoFeignClient;
import org.centro.springcloud.msvc_ordeninspeccion.infrastructure.repositories.OrdenInspeccionJpaRepository;
import org.centro.springcloud.msvc_ordeninspeccion.application.service.OrdenInspeccionService;
import org.centro.springcloud.msvc_ordeninspeccion.domain.port.out.ClientePort;
import org.centro.springcloud.msvc_ordeninspeccion.domain.port.out.OrdenInspeccionRepositoryPort;
import org.centro.springcloud.msvc_ordeninspeccion.domain.port.out.VehiculoPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    OrdenInspeccionRepositoryPort ordenInspeccionRepositoryPort(OrdenInspeccionJpaRepository repository) {
        return new OrdenInspeccionJpaAdapter(repository);
    }

    @Bean
    ClientePort clientePort(ClienteFeignClient client) {
        return new ClienteClientAdapter(client);
    }

    @Bean
    VehiculoPort vehiculoPort(VehiculoFeignClient client) {
        return new VehiculoClientAdapter(client);
    }

    @Bean
    OrdenInspeccionService ordenInspeccionService(OrdenInspeccionRepositoryPort repository,
                                                   ClientePort clientePort,
                                                   VehiculoPort vehiculoPort) {
        return new OrdenInspeccionService(repository, clientePort, vehiculoPort);
    }
}
