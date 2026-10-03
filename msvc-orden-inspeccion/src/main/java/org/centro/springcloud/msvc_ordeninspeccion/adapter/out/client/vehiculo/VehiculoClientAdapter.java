package org.centro.springcloud.msvc_ordeninspeccion.adapter.out.client.vehiculo;

import feign.FeignException;
import org.centro.springcloud.msvc_ordeninspeccion.domain.port.out.VehiculoClientPort;
import org.springframework.stereotype.Component;

@Component
public class VehiculoClientAdapter implements VehiculoClientPort {

    private final VehiculoFeignClient vehiculoFeignClient;

    public VehiculoClientAdapter(
            VehiculoFeignClient vehiculoFeignClient) {

        this.vehiculoFeignClient = vehiculoFeignClient;
    }

    @Override
    public boolean existeVehiculo(Long vehiculoId) {

        try {
            VehiculoResponse vehiculo =
                    vehiculoFeignClient.buscarPorId(vehiculoId);

            return vehiculo != null;

        } catch (FeignException.NotFound e) {

            return false;
        }
    }
}