package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model;

public class RangoPermitido {
    private final Double minimo;
    private final Double maximo;

    public RangoPermitido(Double minimo, Double maximo) {
        if (minimo == null || maximo == null || minimo > maximo) {
            throw new IllegalArgumentException("El rango permitido es invalido");
        }
        this.minimo = minimo;
        this.maximo = maximo;
    }
    public boolean contiene(Double valor) { return valor != null && valor >= minimo && valor <= maximo; }
    public Double getMinimo() { return minimo; }
    public Double getMaximo() { return maximo; }
}
