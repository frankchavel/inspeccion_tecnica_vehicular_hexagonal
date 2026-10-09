package org.centro.springcloud.msvc_ordeninspeccion.infrastructure.adapters;

import java.util.List;
import java.util.Optional;
import org.centro.springcloud.msvc_ordeninspeccion.domain.model.OrdenInspeccion;
import org.centro.springcloud.msvc_ordeninspeccion.domain.port.out.OrdenInspeccionRepositoryPort;
import org.centro.springcloud.msvc_ordeninspeccion.infrastructure.entities.OrdenInspeccionEntity;
import org.centro.springcloud.msvc_ordeninspeccion.infrastructure.repositories.OrdenInspeccionJpaRepository;

public class OrdenInspeccionJpaAdapter implements OrdenInspeccionRepositoryPort {
    private final OrdenInspeccionJpaRepository repository;

    public OrdenInspeccionJpaAdapter(OrdenInspeccionJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<OrdenInspeccion> listar() {
        return repository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public Optional<OrdenInspeccion> buscarPorId(Long id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public OrdenInspeccion guardar(OrdenInspeccion orden) {
        return toDomain(repository.save(toEntity(orden)));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    private OrdenInspeccion toDomain(OrdenInspeccionEntity entity) {
        return new OrdenInspeccion(entity.getOrdenInspeccionId(), entity.getClienteId(),
                entity.getVehiculoId(), entity.getTipoOrden(), entity.getEstado(), entity.getFechaCreacion());
    }

    private OrdenInspeccionEntity toEntity(OrdenInspeccion orden) {
        OrdenInspeccionEntity entity = new OrdenInspeccionEntity();
        entity.setOrdenInspeccionId(orden.getOrdenInspeccionId());
        entity.setClienteId(orden.getClienteId());
        entity.setVehiculoId(orden.getVehiculoId());
        entity.setTipoOrden(orden.getTipoOrden());
        entity.setEstado(orden.getEstado());
        entity.setFechaCreacion(orden.getFechaCreacion());
        return entity;
    }
}
