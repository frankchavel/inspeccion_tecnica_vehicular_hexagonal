package org.centro.springcloud.msvc.msvc_cliente.application.service;

import java.util.List;
import java.util.Optional;
import org.centro.springcloud.msvc.msvc_cliente.domain.model.Cliente;
import org.centro.springcloud.msvc.msvc_cliente.domain.port.in.ActualizarClienteUseCase;
import org.centro.springcloud.msvc.msvc_cliente.domain.port.in.CrearClienteUseCase;
import org.centro.springcloud.msvc.msvc_cliente.domain.port.in.EliminarClienteUseCase;
import org.centro.springcloud.msvc.msvc_cliente.domain.port.in.ObtenerClienteUseCase;
import org.centro.springcloud.msvc.msvc_cliente.domain.port.out.ClienteRepositoryPort;
import org.springframework.transaction.annotation.Transactional;

public class ClienteService implements CrearClienteUseCase, ObtenerClienteUseCase,
        ActualizarClienteUseCase, EliminarClienteUseCase {

    private final ClienteRepositoryPort repository;

    public ClienteService(ClienteRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listar() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Cliente crear(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    @Transactional
    public Optional<Cliente> actualizar(Long id, Cliente cliente) {
        return repository.findById(id).map(actual -> {
            actual.setNumeroLicencia(cliente.getNumeroLicencia());
            actual.setNombres(cliente.getNombres());
            actual.setApellidos(cliente.getApellidos());
            return repository.save(actual);
        });
    }

    @Override
    @Transactional
    public boolean eliminar(Long id) {
        if (repository.findById(id).isEmpty()) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
