package org.centro.springcloud.msvc.msvc_cliente.application.service;

import java.util.List;
import java.util.Optional;
import org.centro.springcloud.msvc.msvc_cliente.application.usecase.ActualizarClienteUseCase;
import org.centro.springcloud.msvc.msvc_cliente.application.usecase.CrearClienteUseCase;
import org.centro.springcloud.msvc.msvc_cliente.application.usecase.EliminarClienteUseCase;
import org.centro.springcloud.msvc.msvc_cliente.application.usecase.ObtenerClienteUseCase;
import org.centro.springcloud.msvc.msvc_cliente.domain.model.Cliente;
import org.centro.springcloud.msvc.msvc_cliente.domain.port.out.ClienteRepositoryPort;

public class ClienteService implements CrearClienteUseCase, ObtenerClienteUseCase,
        ActualizarClienteUseCase, EliminarClienteUseCase {

    private final ClienteRepositoryPort repository;

    public ClienteService(ClienteRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<Cliente> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Cliente> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Cliente crear(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public Optional<Cliente> actualizar(Long id, Cliente cliente) {
        return repository.findById(id).map(actual -> {
            actual.actualizarDatos(cliente.getNombres(), cliente.getApellidos());
            return repository.save(actual);
        });
    }

    @Override
    public boolean eliminar(Long id) {
        if (repository.findById(id).isEmpty()) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
