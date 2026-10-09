package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.port.out;

import java.util.Optional;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model.OrdenInspeccionConsultada;

public interface OrdenInspeccionPort {
    Optional<OrdenInspeccionConsultada> obtenerPorId(Long ordenInspeccionId);
}
