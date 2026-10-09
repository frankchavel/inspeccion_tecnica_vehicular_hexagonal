package org.centro.springcloud.msvc_ordeninspeccion.domain.port.in;

import org.centro.springcloud.msvc_ordeninspeccion.domain.model.OrdenInspeccion;

public interface CrearOrdenInspeccionPort {
    OrdenInspeccion crear(OrdenInspeccion orden);
}
