package org.centro.springcloud.msvc_ordeninspeccion.infrastructure.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.centro.springcloud.msvc_ordeninspeccion.domain.model.EstadoOrden;
import org.centro.springcloud.msvc_ordeninspeccion.domain.model.TipoOrden;

@Entity
@Table(name = "ordenes_inspeccion")
public class OrdenInspeccionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordenInspeccionId;

    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @Column(name = "vehiculo_id", nullable = false)
    private Long vehiculoId;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_orden", nullable = false, length = 30)
    private TipoOrden tipoOrden;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoOrden estado;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    public OrdenInspeccionEntity() { }
    public Long getOrdenInspeccionId() { return ordenInspeccionId; }
    public void setOrdenInspeccionId(Long ordenInspeccionId) { this.ordenInspeccionId = ordenInspeccionId; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public Long getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(Long vehiculoId) { this.vehiculoId = vehiculoId; }
    public TipoOrden getTipoOrden() { return tipoOrden; }
    public void setTipoOrden(TipoOrden tipoOrden) { this.tipoOrden = tipoOrden; }
    public EstadoOrden getEstado() { return estado; }
    public void setEstado(EstadoOrden estado) { this.estado = estado; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}
