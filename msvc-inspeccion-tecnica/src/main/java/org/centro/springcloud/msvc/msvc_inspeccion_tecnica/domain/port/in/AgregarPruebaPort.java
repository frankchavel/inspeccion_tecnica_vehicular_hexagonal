package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.port.in;
import java.util.Optional;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model.InspeccionTecnica;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model.PruebaTecnica;
public interface AgregarPruebaPort { Optional<InspeccionTecnica> agregarPrueba(Long id, PruebaTecnica prueba); }
