package org.centro.msvc_vehiculo.domain.model;

/**
 * Modelo de dominio puro para Vehiculo.
 * No contiene anotaciones de Spring, JPA, Hibernate ni Jakarta.
 */
public class Vehiculo {

    private Long vehiculoId;
    private String placa;
    private String marca;
    private String modelo;
    private Integer anioFabricacion;

    public Vehiculo() {
    }

    public Vehiculo(Long vehiculoId, String placa, String marca, String modelo, Integer anioFabricacion) {
        this.vehiculoId = vehiculoId;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anioFabricacion = anioFabricacion;
    }

    public Long getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Long vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnioFabricacion() {
        return anioFabricacion;
    }

    public void setAnioFabricacion(Integer anioFabricacion) {
        this.anioFabricacion = anioFabricacion;
    }
}
