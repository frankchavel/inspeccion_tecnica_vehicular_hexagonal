package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.infrastructure.dto;
import jakarta.validation.constraints.NotNull;
public class CrearInspeccionRequest {
    @NotNull(message = "La orden de inspeccion es obligatoria") private Long ordenInspeccionId;
    @NotNull(message = "El vehiculo es obligatorio") private Long vehiculoId;
    public Long getOrdenInspeccionId() { return ordenInspeccionId; }
    public void setOrdenInspeccionId(Long ordenInspeccionId) { this.ordenInspeccionId = ordenInspeccionId; }
    public Long getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(Long vehiculoId) { this.vehiculoId = vehiculoId; }
}
