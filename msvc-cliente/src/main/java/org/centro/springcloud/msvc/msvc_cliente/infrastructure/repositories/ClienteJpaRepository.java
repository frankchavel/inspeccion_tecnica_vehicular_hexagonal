package org.centro.springcloud.msvc.msvc_cliente.infrastructure.repositories;

import org.centro.springcloud.msvc.msvc_cliente.infrastructure.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, Long> {
}
