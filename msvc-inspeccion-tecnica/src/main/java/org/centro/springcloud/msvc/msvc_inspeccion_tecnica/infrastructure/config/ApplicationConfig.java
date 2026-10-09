package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.infrastructure.config;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.application.service.InspeccionTecnicaService;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.port.out.*;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.infrastructure.adapters.*;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.infrastructure.clients.OrdenInspeccionFeignClient;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.infrastructure.repositories.InspeccionTecnicaJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class ApplicationConfig {
    @Bean InspeccionTecnicaRepositoryPort inspeccionRepository(InspeccionTecnicaJpaRepository repository) {
        return new InspeccionTecnicaJpaAdapter(repository);
    }
    @Bean OrdenInspeccionPort ordenInspeccionPort(OrdenInspeccionFeignClient client) {
        return new OrdenInspeccionClientAdapter(client);
    }
    @Bean InspeccionTecnicaService inspeccionService(InspeccionTecnicaRepositoryPort repository, OrdenInspeccionPort ordenPort) {
        return new InspeccionTecnicaService(repository, ordenPort);
    }
}
