package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.infrastructure.repositories;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.infrastructure.entities.InspeccionTecnicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface InspeccionTecnicaJpaRepository extends JpaRepository<InspeccionTecnicaEntity, Long> { }
