package org.centro.springcloud.msvc_ordeninspeccion.domain.port.out;

import java.util.Optional;

public interface VehiculoPort {
    Optional<Boolean> obtenerHabilitacion(Long vehiculoId);
}
