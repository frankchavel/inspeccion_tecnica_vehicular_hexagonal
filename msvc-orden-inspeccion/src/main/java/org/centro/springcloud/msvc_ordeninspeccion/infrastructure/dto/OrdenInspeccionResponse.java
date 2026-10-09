package org.centro.springcloud.msvc_ordeninspeccion.infrastructure.dto;

import java.time.LocalDateTime;
import org.centro.springcloud.msvc_ordeninspeccion.domain.model.EstadoOrden;
import org.centro.springcloud.msvc_ordeninspeccion.domain.model.OrdenInspeccion;
import org.centro.springcloud.msvc_ordeninspeccion.domain.model.TipoOrden;

public class OrdenInspeccionResponse {
    private final Long ordenInspeccionId;
    private final Long clienteId;
    private final Long vehiculoId;
    private final TipoOrden tipoOrden;
    private final EstadoOrden estado;
    private final LocalDateTime fechaCreacion;
    private final Boolean habilitada;

    private OrdenInspeccionResponse(OrdenInspeccion orden) {
        this.ordenInspeccionId = orden.getOrdenInspeccionId();
        this.clienteId = orden.getClienteId();
        this.vehiculoId = orden.getVehiculoId();
        this.tipoOrden = orden.getTipoOrden();
        this.estado = orden.getEstado();
        this.fechaCreacion = orden.getFechaCreacion();
        this.habilitada = orden.estaHabilitada();
    }

    public static OrdenInspeccionResponse fromDomain(OrdenInspeccion orden) {
        return new OrdenInspeccionResponse(orden);
    }

    public Long getOrdenInspeccionId() { return ordenInspeccionId; }
    public Long getClienteId() { return clienteId; }
    public Long getVehiculoId() { return vehiculoId; }
    public TipoOrden getTipoOrden() { return tipoOrden; }
    public EstadoOrden getEstado() { return estado; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public Boolean getHabilitada() { return habilitada; }
}
