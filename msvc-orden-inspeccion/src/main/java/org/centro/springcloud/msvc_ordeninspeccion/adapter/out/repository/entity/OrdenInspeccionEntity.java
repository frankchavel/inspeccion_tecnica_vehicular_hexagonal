package org.centro.springcloud.msvc_ordeninspeccion.adapter.out.repository.entity;

import jakarta.persistence.*;

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

    @Column(nullable = false, length = 50)
    private String tipo;

    @Column(length = 30)
    private String estado;

    public OrdenInspeccionEntity() {
    }

    public Long getOrdenInspeccionId() {
        return ordenInspeccionId;
    }

    public void setOrdenInspeccionId(Long ordenInspeccionId) {
        this.ordenInspeccionId = ordenInspeccionId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Long vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}