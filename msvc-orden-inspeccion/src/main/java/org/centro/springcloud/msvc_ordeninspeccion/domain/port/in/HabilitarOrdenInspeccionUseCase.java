package org.centro.springcloud.msvc_ordeninspeccion.domain.port.in;

import org.centro.springcloud.msvc_ordeninspeccion.domain.models.OrdenInspeccion;

public interface HabilitarOrdenInspeccionUseCase {

    OrdenInspeccion habilitar(Long id);
}