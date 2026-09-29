package org.centro.msvc_vehiculo.infrastructure.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Entidad JPA que representa la tabla "vehiculo" en la base de datos.
 * Solo existe en la capa de infraestructura.
 */
@Entity
@Table(name = "vehiculo")
public class VehiculoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehiculo_id")
    private Long vehiculoId;

    @NotBlank(message = "La placa no puede estar vacía")
    @Column(name="placa", unique = true, nullable = false)
    private String placa;

    @NotBlank(message = "La marca no puede estar vacía")
    @Column(name = "marca")
    private String marca;

    @NotBlank(message = "El modelo no puede estar vacío")
    @Column(name = "modelo")
    private String modelo;

    @NotNull(message = "El año de fabricación no puede ser nulo")
    @Column(name = "anio_fabricacion")
    private Integer anioFabricacion;

    public VehiculoEntity() {
    }

    public VehiculoEntity(Long vehiculoId, String placa, String marca, String modelo, Integer anioFabricacion) {
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
