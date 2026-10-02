package org.centro.springcloud.msvc.msvc_cliente.domain.port.in;

import java.util.List;
import java.util.Optional;
import org.centro.springcloud.msvc.msvc_cliente.domain.model.Cliente;

public interface ObtenerClienteUseCase {
    List<Cliente> listar();

    Optional<Cliente> porId(Long id);
}
