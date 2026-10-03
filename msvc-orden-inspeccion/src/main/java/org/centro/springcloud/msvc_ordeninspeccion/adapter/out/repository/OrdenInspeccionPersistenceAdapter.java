package org.centro.springcloud.msvc_ordeninspeccion.adapter.out.repository;

import org.centro.springcloud.msvc_ordeninspeccion.adapter.out.repository.entity.OrdenInspeccionEntity;
import org.centro.springcloud.msvc_ordeninspeccion.domain.models.OrdenInspeccion;
import org.centro.springcloud.msvc_ordeninspeccion.domain.port.out.OrdenInspeccionRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrdenInspeccionPersistenceAdapter
        implements OrdenInspeccionRepositoryPort {

    private final OrdenInspeccionJpaRepository repository;

    public OrdenInspeccionPersistenceAdapter(
            OrdenInspeccionJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<OrdenInspeccion> listar() {

        return repository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Optional<OrdenInspeccion> buscarPorId(Long id) {

        return repository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public OrdenInspeccion guardar(OrdenInspeccion orden) {

        OrdenInspeccionEntity entity = toEntity(orden);

        OrdenInspeccionEntity guardado =
                repository.save(entity);

        return toDomain(guardado);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    private OrdenInspeccion toDomain(
            OrdenInspeccionEntity entity) {

        return new OrdenInspeccion(
                entity.getOrdenInspeccionId(),
                entity.getClienteId(),
                entity.getVehiculoId(),
                entity.getTipo(),
                entity.getEstado()
        );
    }

    private OrdenInspeccionEntity toEntity(
            OrdenInspeccion orden) {

        OrdenInspeccionEntity entity =
                new OrdenInspeccionEntity();

        entity.setOrdenInspeccionId(
                orden.getOrdenInspeccionId()
        );

        entity.setClienteId(
                orden.getClienteId()
        );

        entity.setVehiculoId(
                orden.getVehiculoId()
        );

        entity.setTipo(
                orden.getTipo()
        );

        entity.setEstado(
                orden.getEstado()
        );

        return entity;
    }
}