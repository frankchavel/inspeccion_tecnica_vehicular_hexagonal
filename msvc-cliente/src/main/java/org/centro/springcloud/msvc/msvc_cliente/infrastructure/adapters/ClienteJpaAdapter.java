package org.centro.springcloud.msvc.msvc_cliente.infrastructure.adapters;

import java.util.List;
import java.util.Optional;
import org.centro.springcloud.msvc.msvc_cliente.domain.model.Cliente;
import org.centro.springcloud.msvc.msvc_cliente.domain.model.LicenciaConducir;
import org.centro.springcloud.msvc.msvc_cliente.domain.port.out.ClienteRepositoryPort;
import org.centro.springcloud.msvc.msvc_cliente.infrastructure.entities.ClienteEntity;
import org.centro.springcloud.msvc.msvc_cliente.infrastructure.repositories.ClienteJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class ClienteJpaAdapter implements ClienteRepositoryPort {

    private final ClienteJpaRepository repository;

    public ClienteJpaAdapter(ClienteJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Cliente> findAll() {
        return repository.findAll().stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return toDomain(repository.save(toEntity(cliente)));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private ClienteEntity toEntity(Cliente cliente) {
        LicenciaConducir licencia = cliente.getLicenciaConducir();
        return new ClienteEntity(
                cliente.getClienteId(),
                cliente.getDocumentoIdentidad(),
                cliente.getNombres(),
                cliente.getApellidos(),
                licencia.getNumero(),
                licencia.getFechaVencimiento()
        );
    }

    private Cliente toDomain(ClienteEntity entity) {
        LicenciaConducir licencia = new LicenciaConducir(
                entity.getNumeroLicencia(),
                entity.getFechaVencimientoLicencia()
        );
        return new Cliente(
                entity.getClienteId(),
                entity.getDocumentoIdentidad(),
                entity.getNombres(),
                entity.getApellidos(),
                licencia
        );
    }
}
