package org.centro.springcloud.msvc_ordeninspeccion.adapter.out.repository;

import org.centro.springcloud.msvc_ordeninspeccion.adapter.out.repository.entity.OrdenInspeccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenInspeccionJpaRepository
        extends JpaRepository<OrdenInspeccionEntity, Long> {
}