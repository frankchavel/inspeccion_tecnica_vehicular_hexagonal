package org.centro.springcloud.msvc_ordeninspeccion.adapter.in.web.dto;

import org.centro.springcloud.msvc_ordeninspeccion.domain.models.OrdenInspeccion;

public class OrdenInspeccionResponse {

    private Long ordenInspeccionId;
    private Long clienteId;
    private Long vehiculoId;
    private String tipo;
    private String estado;

    public OrdenInspeccionResponse() {
    }

    public OrdenInspeccionResponse(
            Long ordenInspeccionId,
            Long clienteId,
            Long vehiculoId,
            String tipo,
            String estado) {

        this.ordenInspeccionId = ordenInspeccionId;
        this.clienteId = clienteId;
        this.vehiculoId = vehiculoId;
        this.tipo = tipo;
        this.estado = estado;
    }

    public static OrdenInspeccionResponse fromDomain(
            OrdenInspeccion orden) {

        return new OrdenInspeccionResponse(
                orden.getOrdenInspeccionId(),
                orden.getClienteId(),
                orden.getVehiculoId(),
                orden.getTipo(),
                orden.getEstado()
        );
    }

    public Long getOrdenInspeccionId() {
        return ordenInspeccionId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public Long getVehiculoId() {
        return vehiculoId;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEstado() {
        return estado;
    }
}