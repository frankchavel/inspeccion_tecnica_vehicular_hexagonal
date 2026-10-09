package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model;

import java.util.ArrayList;
import java.util.List;

public class PruebaTecnica {
    private final Long pruebaId;
    private final TipoPrueba tipoPrueba;
    private Double valorObtenido;
    private final RangoPermitido rangoPermitido;
    private ResultadoPrueba resultado;
    private final List<DefectoDetectado> defectos;

    public PruebaTecnica(Long pruebaId, TipoPrueba tipoPrueba, Double valorObtenido,
                         RangoPermitido rangoPermitido, ResultadoPrueba resultado,
                         List<DefectoDetectado> defectos) {
        if (tipoPrueba == null) throw new IllegalArgumentException("El tipo de prueba es obligatorio");
        if (rangoPermitido == null) throw new IllegalArgumentException("El rango permitido es obligatorio");
        this.pruebaId = pruebaId;
        this.tipoPrueba = tipoPrueba;
        this.valorObtenido = valorObtenido;
        this.rangoPermitido = rangoPermitido;
        this.resultado = resultado == null ? ResultadoPrueba.PENDIENTE : resultado;
        this.defectos = defectos == null ? new ArrayList<>() : new ArrayList<>(defectos);
    }

    public void evaluarResultado() {
        resultado = rangoPermitido.contiene(valorObtenido)
                ? ResultadoPrueba.APROBADO : ResultadoPrueba.DESAPROBADO;
    }

    public void registrarMedicion(Double valorObtenido) {
        if (valorObtenido == null) throw new IllegalArgumentException("El valor obtenido es obligatorio");
        this.valorObtenido = valorObtenido;
        evaluarResultado();
    }
    public boolean tieneDefectosGraves() {
        return defectos.stream().anyMatch(d -> d.esGrave() || d.esMuyGrave());
    }
    public void registrarDefecto(DefectoDetectado defecto) {
        if (defecto == null) throw new IllegalArgumentException("El defecto es obligatorio");
        defectos.add(defecto);
    }
    public Long getPruebaId() { return pruebaId; }
    public TipoPrueba getTipoPrueba() { return tipoPrueba; }
    public Double getValorObtenido() { return valorObtenido; }
    public RangoPermitido getRangoPermitido() { return rangoPermitido; }
    public ResultadoPrueba getResultado() { return resultado; }
    public List<DefectoDetectado> getDefectos() { return List.copyOf(defectos); }
}
