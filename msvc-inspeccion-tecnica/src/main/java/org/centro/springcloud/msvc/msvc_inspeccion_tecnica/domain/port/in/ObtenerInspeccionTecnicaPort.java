package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.port.in;
import java.util.List;
import java.util.Optional;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model.InspeccionTecnica;
public interface ObtenerInspeccionTecnicaPort {
    List<InspeccionTecnica> listar();
    Optional<InspeccionTecnica> obtenerPorId(Long id);
}
