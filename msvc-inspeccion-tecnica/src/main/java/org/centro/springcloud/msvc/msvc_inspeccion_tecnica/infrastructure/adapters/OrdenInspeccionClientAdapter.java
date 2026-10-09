package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.infrastructure.adapters;
import feign.FeignException;
import java.util.Optional;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.port.out.OrdenInspeccionPort;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model.OrdenInspeccionConsultada;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.infrastructure.clients.OrdenInspeccionFeignClient;
public class OrdenInspeccionClientAdapter implements OrdenInspeccionPort {
    private final OrdenInspeccionFeignClient client;
    public OrdenInspeccionClientAdapter(OrdenInspeccionFeignClient client) { this.client = client; }
    @Override public Optional<OrdenInspeccionConsultada> obtenerPorId(Long id) {
        try {
            var orden = client.obtenerPorId(id);
            return orden == null ? Optional.empty() : Optional.of(new OrdenInspeccionConsultada(
                    orden.getVehiculoId(), Boolean.TRUE.equals(orden.getHabilitada())));
        } catch (FeignException.NotFound exception) {
            return Optional.empty();
        }
    }
}
