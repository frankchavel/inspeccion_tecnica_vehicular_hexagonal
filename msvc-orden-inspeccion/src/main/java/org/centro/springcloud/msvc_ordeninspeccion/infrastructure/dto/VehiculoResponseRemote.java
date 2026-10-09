package org.centro.springcloud.msvc_ordeninspeccion.infrastructure.dto;

public class VehiculoResponseRemote {
    private Long vehiculoId;
    private String placa;
    private String marca;
    private String modelo;
    private Boolean habilitado;

    public VehiculoResponseRemote() { }
    public Long getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(Long vehiculoId) { this.vehiculoId = vehiculoId; }
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public Boolean getHabilitado() { return habilitado; }
    public void setHabilitado(Boolean habilitado) { this.habilitado = habilitado; }
}
