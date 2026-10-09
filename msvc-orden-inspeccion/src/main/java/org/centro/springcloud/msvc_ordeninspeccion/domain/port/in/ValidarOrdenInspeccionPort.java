package org.centro.springcloud.msvc_ordeninspeccion.domain.port.in;

import org.centro.springcloud.msvc_ordeninspeccion.domain.model.OrdenInspeccion;

public interface ValidarOrdenInspeccionPort {
    OrdenInspeccion validar(Long id);
}
