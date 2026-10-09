package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model;

public class OrdenInspeccionConsultada {
    private final Long vehiculoId;
    private final boolean habilitada;

    public OrdenInspeccionConsultada(Long vehiculoId, boolean habilitada) {
        this.vehiculoId = vehiculoId;
        this.habilitada = habilitada;
    }

    public Long getVehiculoId() {
        return vehiculoId;
    }

    public boolean isHabilitada() {
        return habilitada;
    }
}
