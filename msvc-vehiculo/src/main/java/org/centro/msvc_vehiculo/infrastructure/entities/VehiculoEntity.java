package org.centro.msvc_vehiculo.infrastructure.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "vehiculo")
public class VehiculoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehiculo_id")
    private Long vehiculoId;

    @Column(nullable = false, unique = true)
    private String placa;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(name = "anio_fabricacion", nullable = false)
    private Integer anioFabricacion;

    @Column(name = "numero_soat", nullable = false)
    private String numeroSoat;

    @Column(name = "fecha_vencimiento_soat", nullable = false)
    private LocalDate fechaVencimientoSoat;

    @Column(name = "numero_titulo_propiedad", nullable = false)
    private String numeroTituloPropiedad;

    @Column(name = "titulo_valido", nullable = false)
    private Boolean tituloValido;

    public VehiculoEntity() {
    }

    public VehiculoEntity(Long vehiculoId, String placa, String marca, String modelo,
            Integer anioFabricacion, String numeroSoat, LocalDate fechaVencimientoSoat,
            String numeroTituloPropiedad, Boolean tituloValido) {
        this.vehiculoId = vehiculoId;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anioFabricacion = anioFabricacion;
        this.numeroSoat = numeroSoat;
        this.fechaVencimientoSoat = fechaVencimientoSoat;
        this.numeroTituloPropiedad = numeroTituloPropiedad;
        this.tituloValido = tituloValido;
    }

    public Long getVehiculoId() {
        return vehiculoId;
    }

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public Integer getAnioFabricacion() {
        return anioFabricacion;
    }

    public String getNumeroSoat() {
        return numeroSoat;
    }

    public LocalDate getFechaVencimientoSoat() {
        return fechaVencimientoSoat;
    }

    public String getNumeroTituloPropiedad() {
        return numeroTituloPropiedad;
    }

    public Boolean getTituloValido() {
        return tituloValido;
    }
}
