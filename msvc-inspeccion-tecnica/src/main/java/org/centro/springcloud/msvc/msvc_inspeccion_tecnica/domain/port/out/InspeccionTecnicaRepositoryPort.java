package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.port.out;
import java.util.List;
import java.util.Optional;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model.InspeccionTecnica;
public interface InspeccionTecnicaRepositoryPort {
    InspeccionTecnica guardar(InspeccionTecnica inspeccion);
    List<InspeccionTecnica> listar();
    Optional<InspeccionTecnica> buscarPorId(Long id);
    void eliminar(Long id);
}
