package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.infrastructure.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model.DefectoDetectado;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model.SeveridadDefecto;
public class RegistrarDefectoRequest {
    @NotBlank private String descripcion;
    @NotNull private SeveridadDefecto severidad;
    public DefectoDetectado toDomain() { return new DefectoDetectado(null, descripcion, severidad); }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public SeveridadDefecto getSeveridad() { return severidad; }
    public void setSeveridad(SeveridadDefecto severidad) { this.severidad = severidad; }
}
