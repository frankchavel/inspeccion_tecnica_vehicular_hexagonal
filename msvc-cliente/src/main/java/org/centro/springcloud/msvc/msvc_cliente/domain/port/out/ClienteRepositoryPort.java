package org.centro.springcloud.msvc.msvc_cliente.domain.port.out;

import java.util.List;
import java.util.Optional;
import org.centro.springcloud.msvc.msvc_cliente.domain.model.Cliente;

public interface ClienteRepositoryPort {
    List<Cliente> findAll();

    Optional<Cliente> findById(Long id);

    Cliente save(Cliente cliente);

    void deleteById(Long id);
}
