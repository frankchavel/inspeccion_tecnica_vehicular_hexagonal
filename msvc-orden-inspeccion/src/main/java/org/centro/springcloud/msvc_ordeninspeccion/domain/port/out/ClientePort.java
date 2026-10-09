package org.centro.springcloud.msvc_ordeninspeccion.domain.port.out;

import java.util.Optional;

public interface ClientePort {
    Optional<Boolean> obtenerHabilitacion(Long clienteId);
}
