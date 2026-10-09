package org.centro.springcloud.msvc_ordeninspeccion.infrastructure.dto;

import jakarta.validation.constraints.NotNull;
import org.centro.springcloud.msvc_ordeninspeccion.domain.model.TipoOrden;

public class OrdenInspeccionRequest {
    @NotNull(message = "El ID del cliente es obligatorio")
    private Long clienteId;

    @NotNull(message = "El ID del vehículo es obligatorio")
    private Long vehiculoId;

    @NotNull(message = "El tipo de orden es obligatorio")
    private TipoOrden tipoOrden;

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public Long getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(Long vehiculoId) { this.vehiculoId = vehiculoId; }
    public TipoOrden getTipoOrden() { return tipoOrden; }
    public void setTipoOrden(TipoOrden tipoOrden) { this.tipoOrden = tipoOrden; }
}
