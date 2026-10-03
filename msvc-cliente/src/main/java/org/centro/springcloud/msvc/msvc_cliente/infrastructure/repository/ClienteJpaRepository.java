package org.centro.springcloud.msvc.msvc_cliente.infrastructure.repository;

import org.centro.springcloud.msvc.msvc_cliente.infrastructure.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, Long> {
}
