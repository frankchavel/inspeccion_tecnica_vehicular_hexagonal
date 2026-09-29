package org.centro.msvc_vehiculo.infrastructure.adapters;

import org.centro.msvc_vehiculo.domain.model.Vehiculo;
import org.centro.msvc_vehiculo.domain.port.out.VehiculoRepositoryPort;
import org.centro.msvc_vehiculo.infrastructure.entities.VehiculoEntity;
import org.centro.msvc_vehiculo.infrastructure.repositories.VehiculoJpaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Adaptador de persistencia que implementa VehiculoRepositoryPort.
 * Hace el mapeo bidireccional entre Vehiculo (dominio) y VehiculoEntity (JPA).
 */
@Component
public class VehiculoJpaAdapter implements VehiculoRepositoryPort {

    private final VehiculoJpaRepository jpaRepository;

    public VehiculoJpaAdapter(VehiculoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    // ---- Mapeo Dominio -> Entidad ----

    private VehiculoEntity toEntity(Vehiculo vehiculo) {
        return new VehiculoEntity(
                vehiculo.getVehiculoId(),
                vehiculo.getPlaca(),
                vehiculo.getMarca(),
                vehiculo.getModelo(),
                vehiculo.getAnioFabricacion()
        );
    }

    // ---- Mapeo Entidad -> Dominio ----

    private Vehiculo toDomain(VehiculoEntity entity) {
        return new Vehiculo(
                entity.getVehiculoId(),
                entity.getPlaca(),
                entity.getMarca(),
                entity.getModelo(),
                entity.getAnioFabricacion()
        );
    }

    @Override
    public Vehiculo save(Vehiculo vehiculo) {
        VehiculoEntity entity = toEntity(vehiculo);
        VehiculoEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Vehiculo> findById(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Vehiculo> findAll() {
        List<Vehiculo> result = new ArrayList<>();
        jpaRepository.findAll().forEach(entity -> result.add(toDomain(entity)));
        return result;
    }

    @Override
    public Optional<Vehiculo> findByPlaca(String placa) {
        return jpaRepository.findByPlaca(placa).map(this::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }
}
