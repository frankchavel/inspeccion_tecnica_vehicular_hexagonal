package org.centro.springcloud.msvc_ordeninspeccion.domain.port.in;

import org.centro.springcloud.msvc_ordeninspeccion.domain.model.OrdenInspeccion;
import org.centro.springcloud.msvc_ordeninspeccion.domain.model.TipoOrden;

public interface ActualizarOrdenInspeccionPort {
    OrdenInspeccion actualizar(Long id, Long clienteId, Long vehiculoId, TipoOrden tipoOrden);
}
