package org.centro.springcloud.msvc.msvc_cliente.domain.port.in;

import java.util.Optional;
import org.centro.springcloud.msvc.msvc_cliente.domain.model.Cliente;

public interface ActualizarClientePort {
    Optional<Cliente> actualizar(Long id, Cliente cliente);
}
