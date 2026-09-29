package org.centro.msvc_vehiculo.infrastructure.repositories;

import org.centro.msvc_vehiculo.infrastructure.entities.VehiculoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Repositorio Spring Data JPA para VehiculoEntity.
 * Solo existe en la capa de infraestructura.
 */
public interface VehiculoJpaRepository extends CrudRepository<VehiculoEntity, Long> {

    Optional<VehiculoEntity> findByPlaca(String placa);
}
