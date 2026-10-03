package org.centro.springcloud.msvc_ordeninspeccion.domain.models;

public class OrdenInspeccion {

    private Long ordenInspeccionId;
    private Long clienteId;
    private Long vehiculoId;
    private String tipo;
    private String estado;

    public OrdenInspeccion() {
    }

    public OrdenInspeccion(Long ordenInspeccionId,
                           Long clienteId,
                           Long vehiculoId,
                           String tipo,
                           String estado) {
        this.ordenInspeccionId = ordenInspeccionId;
        this.clienteId = clienteId;
        this.vehiculoId = vehiculoId;
        this.tipo = tipo;
        this.estado = estado;
    }

    public static OrdenInspeccion crear(Long clienteId,
                                        Long vehiculoId,
                                        String tipo) {

        if (clienteId == null) {
            throw new IllegalArgumentException("El ID del cliente es obligatorio");
        }

        if (vehiculoId == null) {
            throw new IllegalArgumentException("El ID del vehículo es obligatorio");
        }

        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException("El tipo de inspección es obligatorio");
        }

        return new OrdenInspeccion(
                null,
                clienteId,
                vehiculoId,
                tipo,
                "REGISTRADA"
        );
    }

    public void actualizar(Long clienteId,
                           Long vehiculoId,
                           String tipo) {

        if (clienteId == null) {
            throw new IllegalArgumentException("El ID del cliente es obligatorio");
        }

        if (vehiculoId == null) {
            throw new IllegalArgumentException("El ID del vehículo es obligatorio");
        }

        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException("El tipo de inspección es obligatorio");
        }

        this.clienteId = clienteId;
        this.vehiculoId = vehiculoId;
        this.tipo = tipo;
    }

    public void habilitar() {
        this.estado = "HABILITADA";
    }

    public void cancelar() {
        this.estado = "CANCELADA";
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