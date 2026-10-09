package org.centro.msvc_vehiculo.infrastructure.repositories;

import org.centro.msvc_vehiculo.infrastructure.entities.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio Spring Data JPA para VehiculoEntity.
 * Solo existe en la capa de infraestructura.
 */
public interface VehiculoJpaRepository extends JpaRepository<VehiculoEntity, Long> {

    boolean existsByPlaca(String placa);
}
