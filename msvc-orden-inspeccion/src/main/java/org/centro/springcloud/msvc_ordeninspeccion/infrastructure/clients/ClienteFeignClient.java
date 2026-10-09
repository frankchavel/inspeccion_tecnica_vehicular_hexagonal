package org.centro.springcloud.msvc_ordeninspeccion.infrastructure.clients;

import org.centro.springcloud.msvc_ordeninspeccion.infrastructure.dto.ClienteResponseRemote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-cliente", url = "${clientes.url}")
public interface ClienteFeignClient {
    @GetMapping("/api/clientes/{id}")
    ClienteResponseRemote buscarPorId(@PathVariable("id") Long id);
}
