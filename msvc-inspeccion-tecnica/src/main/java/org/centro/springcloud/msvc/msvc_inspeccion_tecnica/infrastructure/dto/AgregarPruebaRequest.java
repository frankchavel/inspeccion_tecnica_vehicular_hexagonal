package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model.*;

public class AgregarPruebaRequest {
    @NotBlank private String codigo;
    @NotBlank private String descripcion;
    @NotBlank private String unidadMedida;
    @NotNull private Double valorMinimo;
    @NotNull private Double valorMaximo;
    public PruebaTecnica toDomain() {
        return new PruebaTecnica(null, new TipoPrueba(codigo, descripcion, unidadMedida), null,
                new RangoPermitido(valorMinimo, valorMaximo), ResultadoPrueba.PENDIENTE, List.of());
    }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getUnidadMedida() { return unidadMedida; }
    public void setUnidadMedida(String unidadMedida) { this.unidadMedida = unidadMedida; }
    public Double getValorMinimo() { return valorMinimo; }
    public void setValorMinimo(Double valorMinimo) { this.valorMinimo = valorMinimo; }
    public Double getValorMaximo() { return valorMaximo; }
    public void setValorMaximo(Double valorMaximo) { this.valorMaximo = valorMaximo; }
}
