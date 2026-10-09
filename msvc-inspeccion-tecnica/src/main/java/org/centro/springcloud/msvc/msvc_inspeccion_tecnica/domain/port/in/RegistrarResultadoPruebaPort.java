package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.port.in;
import java.util.Optional;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model.InspeccionTecnica;
public interface RegistrarResultadoPruebaPort {
    Optional<InspeccionTecnica> registrarMedicion(Long id, Long pruebaId, Double valorObtenido);
}
