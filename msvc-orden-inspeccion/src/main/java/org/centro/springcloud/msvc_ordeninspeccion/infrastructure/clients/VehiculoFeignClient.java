package org.centro.springcloud.msvc_ordeninspeccion.infrastructure.clients;

import org.centro.springcloud.msvc_ordeninspeccion.infrastructure.dto.VehiculoResponseRemote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-vehiculo", url = "${vehiculos.url}")
public interface VehiculoFeignClient {
    @GetMapping("/api/vehiculos/{id}")
    VehiculoResponseRemote buscarPorId(@PathVariable("id") Long id);
}
