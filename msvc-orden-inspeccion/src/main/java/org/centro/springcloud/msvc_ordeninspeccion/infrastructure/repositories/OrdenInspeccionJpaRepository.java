package org.centro.springcloud.msvc_ordeninspeccion.infrastructure.repositories;

import org.centro.springcloud.msvc_ordeninspeccion.infrastructure.entities.OrdenInspeccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenInspeccionJpaRepository extends JpaRepository<OrdenInspeccionEntity, Long> {
}
