package org.centro.springcloud.msvc_ordeninspeccion.domain.model;

import java.time.LocalDateTime;

public class OrdenInspeccion {

    private final Long ordenInspeccionId;
    private Long clienteId;
    private Long vehiculoId;
    private TipoOrden tipoOrden;
    private EstadoOrden estado;
    private final LocalDateTime fechaCreacion;

    public OrdenInspeccion(Long ordenInspeccionId,
                           Long clienteId,
                           Long vehiculoId,
                           TipoOrden tipoOrden,
                           EstadoOrden estado,
                           LocalDateTime fechaCreacion) {
        this.ordenInspeccionId = ordenInspeccionId;
        validarDatos(clienteId, vehiculoId, tipoOrden);
        if (estado == null) throw new IllegalArgumentException("El estado es obligatorio");
        if (fechaCreacion == null) throw new IllegalArgumentException("La fecha de creacion es obligatoria");
        this.clienteId = clienteId;
        this.vehiculoId = vehiculoId;
        this.tipoOrden = tipoOrden;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    public static OrdenInspeccion crear(Long clienteId, Long vehiculoId, TipoOrden tipoOrden) {
        return new OrdenInspeccion(null, clienteId, vehiculoId, tipoOrden,
                EstadoOrden.PENDIENTE, LocalDateTime.now());
    }

    public void actualizarDatos(Long clienteId, Long vehiculoId, TipoOrden tipoOrden) {
        validarDatos(clienteId, vehiculoId, tipoOrden);
        this.clienteId = clienteId;
        this.vehiculoId = vehiculoId;
        this.tipoOrden = tipoOrden;
    }

    public void validar() {
        this.estado = EstadoOrden.VALIDADA;
    }

    public void cancelar() {
        this.estado = EstadoOrden.CANCELADA;
    }

    public boolean estaHabilitada() {
        return estado == EstadoOrden.VALIDADA;
    }

    private static void validarDatos(Long clienteId, Long vehiculoId, TipoOrden tipoOrden) {
        if (clienteId == null) throw new IllegalArgumentException("El ID del cliente es obligatorio");
        if (vehiculoId == null) throw new IllegalArgumentException("El ID del vehiculo es obligatorio");
        if (tipoOrden == null) throw new IllegalArgumentException("El tipo de orden es obligatorio");
    }

    public Long getOrdenInspeccionId() { return ordenInspeccionId; }
    public Long getClienteId() { return clienteId; }
    public Long getVehiculoId() { return vehiculoId; }
    public TipoOrden getTipoOrden() { return tipoOrden; }
    public EstadoOrden getEstado() { return estado; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
}
