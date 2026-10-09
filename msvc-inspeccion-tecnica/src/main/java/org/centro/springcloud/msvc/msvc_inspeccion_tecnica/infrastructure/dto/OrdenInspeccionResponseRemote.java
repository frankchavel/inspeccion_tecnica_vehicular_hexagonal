package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.infrastructure.dto;
public class OrdenInspeccionResponseRemote {
    private Long ordenInspeccionId;
    private Long clienteId;
    private Long vehiculoId;
    private String estado;
    private Boolean habilitada;
    public OrdenInspeccionResponseRemote() { }
    public Long getOrdenInspeccionId() { return ordenInspeccionId; }
    public void setOrdenInspeccionId(Long ordenInspeccionId) { this.ordenInspeccionId = ordenInspeccionId; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public Long getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(Long vehiculoId) { this.vehiculoId = vehiculoId; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Boolean getHabilitada() { return habilitada; }
    public void setHabilitada(Boolean habilitada) { this.habilitada = habilitada; }
}
