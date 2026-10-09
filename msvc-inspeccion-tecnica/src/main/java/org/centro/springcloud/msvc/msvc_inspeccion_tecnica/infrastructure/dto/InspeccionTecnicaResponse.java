package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.infrastructure.dto;
import java.time.LocalDateTime;
import java.util.List;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model.*;
public class InspeccionTecnicaResponse {
    private final Long inspeccionId;
    private final Long ordenInspeccionId;
    private final Long vehiculoId;
    private final EstadoInspeccion estado;
    private final DictamenInspeccion dictamen;
    private final LocalDateTime fechaInicio;
    private final LocalDateTime fechaFin;
    private final Integer cantidadPruebas;
    private final List<PruebaTecnicaResponse> pruebas;
    private InspeccionTecnicaResponse(InspeccionTecnica i) {
        inspeccionId=i.getInspeccionId(); ordenInspeccionId=i.getOrdenInspeccionId(); vehiculoId=i.getVehiculoId();
        estado=i.getEstado(); dictamen=i.getDictamen(); fechaInicio=i.getFechaInicio(); fechaFin=i.getFechaFin();
        cantidadPruebas=i.getPruebas().size();
        pruebas=i.getPruebas().stream().map(PruebaTecnicaResponse::fromDomain).toList();
    }
    public static InspeccionTecnicaResponse fromDomain(InspeccionTecnica i) { return new InspeccionTecnicaResponse(i); }
    public Long getInspeccionId() { return inspeccionId; }
    public Long getOrdenInspeccionId() { return ordenInspeccionId; }
    public Long getVehiculoId() { return vehiculoId; }
    public EstadoInspeccion getEstado() { return estado; }
    public DictamenInspeccion getDictamen() { return dictamen; }
    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public LocalDateTime getFechaFin() { return fechaFin; }
    public Integer getCantidadPruebas() { return cantidadPruebas; }
    public List<PruebaTecnicaResponse> getPruebas() { return pruebas; }
}
