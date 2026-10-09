package org.centro.msvc_vehiculo.domain.model;

public class Vehiculo {

    private final Long vehiculoId;
    private final String placa;
    private String marca;
    private String modelo;
    private Integer anioFabricacion;
    private final Soat soat;
    private final TituloPropiedad tituloPropiedad;

    public Vehiculo(Long vehiculoId, String placa, String marca, String modelo,
            Integer anioFabricacion, Soat soat, TituloPropiedad tituloPropiedad) {
        this.vehiculoId = vehiculoId;
        this.placa = validarTexto(placa, "La placa es obligatoria");
        this.marca = validarTexto(marca, "La marca es obligatoria");
        this.modelo = validarTexto(modelo, "El modelo es obligatorio");
        if (anioFabricacion == null) {
            throw new IllegalArgumentException("El año de fabricación es obligatorio");
        }
        if (soat == null) {
            throw new IllegalArgumentException("El SOAT es obligatorio");
        }
        if (tituloPropiedad == null) {
            throw new IllegalArgumentException("El título de propiedad es obligatorio");
        }
        this.anioFabricacion = anioFabricacion;
        this.soat = soat;
        this.tituloPropiedad = tituloPropiedad;
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

    public Soat getSoat() {
        return soat;
    }

    public TituloPropiedad getTituloPropiedad() {
        return tituloPropiedad;
    }

    public boolean tieneSoatVigente() {
        return soat.estaVigente();
    }

    public boolean tieneTituloValido() {
        return tituloPropiedad.esValido();
    }

    public boolean estaHabilitado() {
        return tieneSoatVigente() && tieneTituloValido();
    }

    public void actualizarDatos(String marca, String modelo, Integer anioFabricacion) {
        this.marca = validarTexto(marca, "La marca es obligatoria");
        this.modelo = validarTexto(modelo, "El modelo es obligatorio");
        if (anioFabricacion == null) {
            throw new IllegalArgumentException("El año de fabricación es obligatorio");
        }
        this.anioFabricacion = anioFabricacion;
    }

    private static String validarTexto(String valor, String mensaje) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(mensaje);
        }
        return valor.trim();
    }
}
