package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.infrastructure.clients;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.infrastructure.dto.OrdenInspeccionResponseRemote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "msvc-orden-inspeccion", url = "http://localhost:8040")
public interface OrdenInspeccionFeignClient {
    @GetMapping("/api/ordenes-inspeccion/{id}")
    OrdenInspeccionResponseRemote obtenerPorId(@PathVariable("id") Long id);
}
