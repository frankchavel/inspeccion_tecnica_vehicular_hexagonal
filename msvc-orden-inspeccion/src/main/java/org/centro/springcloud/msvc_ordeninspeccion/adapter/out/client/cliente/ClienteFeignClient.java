package org.centro.springcloud.msvc_ordeninspeccion.adapter.out.client.cliente;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "msvc-cliente",
        url = "${clientes.url}"
)
public interface ClienteFeignClient {

    // Consulta un cliente por su ID.
    @GetMapping("/api/cliente/{id}")
    ClienteResponse buscarPorId(@PathVariable Long id);
}