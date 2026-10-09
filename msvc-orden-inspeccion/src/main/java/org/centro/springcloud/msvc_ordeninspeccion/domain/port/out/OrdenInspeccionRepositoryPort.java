package org.centro.springcloud.msvc_ordeninspeccion.domain.port.out;

import org.centro.springcloud.msvc_ordeninspeccion.domain.model.OrdenInspeccion;

import java.util.List;
import java.util.Optional;

public interface OrdenInspeccionRepositoryPort {

    List<OrdenInspeccion> listar();

    Optional<OrdenInspeccion> buscarPorId(Long id);

    OrdenInspeccion guardar(OrdenInspeccion orden);

    void eliminar(Long id);
}
