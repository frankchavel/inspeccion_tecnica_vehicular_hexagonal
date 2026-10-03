package org.centro.springcloud.msvc_ordeninspeccion.domain.port.in;

import org.centro.springcloud.msvc_ordeninspeccion.domain.models.OrdenInspeccion;

public interface CrearOrdenInspeccionUseCase {

    OrdenInspeccion crear(OrdenInspeccion orden);
}