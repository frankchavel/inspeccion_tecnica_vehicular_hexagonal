package org.centro.springcloud.msvc.msvc_cliente.infrastructure.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.centro.springcloud.msvc.msvc_cliente.domain.model.Cliente;
import org.centro.springcloud.msvc.msvc_cliente.domain.port.out.ClienteRepositoryPort;
import org.centro.springcloud.msvc.msvc_cliente.infrastructure.entity.ClienteEntity;
import org.centro.springcloud.msvc.msvc_cliente.infrastructure.repository.ClienteJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteJpaAdapter implements ClienteRepositoryPort {
    private final ClienteJpaRepository repository;

    public ClienteJpaAdapter(ClienteJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> clientes = new ArrayList<>();
        repository.findAll().forEach(entity -> clientes.add(toDomain(entity)));
        return clientes;
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return repository.findById(id).map(ClienteJpaAdapter::toDomain);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return toDomain(repository.save(toEntity(cliente)));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private static Cliente toDomain(ClienteEntity entity) {
        Cliente cliente = new Cliente();
        cliente.setClienteId(entity.getClienteId());
        cliente.setNumeroLicencia(entity.getNumeroLicencia());
        cliente.setNombres(entity.getNombres());
        cliente.setApellidos(entity.getApellidos());
        return cliente;
    }

    private static ClienteEntity toEntity(Cliente cliente) {
        ClienteEntity entity = new ClienteEntity();
        entity.setClienteId(cliente.getClienteId());
        entity.setNumeroLicencia(cliente.getNumeroLicencia());
        entity.setNombres(cliente.getNombres());
        entity.setApellidos(cliente.getApellidos());
        return entity;
    }
}
