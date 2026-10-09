package org.centro.springcloud.msvc_ordeninspeccion.domain.port.in;

import java.util.List;
import java.util.Optional;
import org.centro.springcloud.msvc_ordeninspeccion.domain.model.OrdenInspeccion;

public interface ObtenerOrdenInspeccionPort {
    List<OrdenInspeccion> listar();
    Optional<OrdenInspeccion> obtenerPorId(Long id);
}
