package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.port.in;
import java.util.Optional;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model.InspeccionTecnica;
public interface ActualizarInspeccionTecnicaPort {
    Optional<InspeccionTecnica> actualizar(Long id, Long ordenInspeccionId, Long vehiculoId);
}
