package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.infrastructure.dto;
import jakarta.validation.constraints.NotNull;
public class RegistrarMedicionRequest {
    @NotNull private Double valorObtenido;
    public Double getValorObtenido() { return valorObtenido; }
    public void setValorObtenido(Double valorObtenido) { this.valorObtenido = valorObtenido; }
}
