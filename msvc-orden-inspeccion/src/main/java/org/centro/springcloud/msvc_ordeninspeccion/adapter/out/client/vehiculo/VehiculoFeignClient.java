package org.centro.springcloud.msvc_ordeninspeccion.adapter.out.client.vehiculo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "msvc-vehiculo",
        url = "${vehiculos.url}"
)
public interface VehiculoFeignClient {

    // Consulta un vehículo por su ID.
    @GetMapping("/api/vehiculo/{id}")
    VehiculoResponse buscarPorId(@PathVariable Long id);
}