package org.centro.springcloud.msvc_ordeninspeccion.infrastructure.adapters;

import feign.FeignException;
import java.util.Optional;
import org.centro.springcloud.msvc_ordeninspeccion.domain.port.out.VehiculoPort;
import org.centro.springcloud.msvc_ordeninspeccion.infrastructure.clients.VehiculoFeignClient;
import org.centro.springcloud.msvc_ordeninspeccion.infrastructure.dto.VehiculoResponseRemote;

public class VehiculoClientAdapter implements VehiculoPort {
    private final VehiculoFeignClient vehiculoFeignClient;

    public VehiculoClientAdapter(VehiculoFeignClient vehiculoFeignClient) {
        this.vehiculoFeignClient = vehiculoFeignClient;
    }

    @Override
    public Optional<Boolean> obtenerHabilitacion(Long vehiculoId) {
        try {
            VehiculoResponseRemote vehiculo = vehiculoFeignClient.buscarPorId(vehiculoId);
            return vehiculo == null
                    ? Optional.empty()
                    : Optional.of(Boolean.TRUE.equals(vehiculo.getHabilitado()));
        } catch (FeignException.NotFound exception) {
            return Optional.empty();
        }
    }
}
