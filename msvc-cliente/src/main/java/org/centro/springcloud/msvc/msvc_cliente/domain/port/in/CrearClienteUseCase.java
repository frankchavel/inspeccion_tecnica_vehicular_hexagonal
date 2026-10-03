package org.centro.springcloud.msvc.msvc_cliente.domain.port.in;

import org.centro.springcloud.msvc.msvc_cliente.domain.model.Cliente;

public interface CrearClienteUseCase {
    Cliente crear(Cliente cliente);
}
