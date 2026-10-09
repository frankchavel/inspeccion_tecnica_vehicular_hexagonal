package org.centro.springcloud.msvc.msvc_cliente.domain.model;

public class Cliente {
    private final Long clienteId;
    private final String documentoIdentidad;
    private String nombres;
    private String apellidos;
    private final LicenciaConducir licenciaConducir;

    public Cliente(Long clienteId, String documentoIdentidad, String nombres,
            String apellidos, LicenciaConducir licenciaConducir) {
        this.clienteId = clienteId;
        this.documentoIdentidad = validarTexto(documentoIdentidad, "El documento de identidad es obligatorio");
        this.nombres = validarTexto(nombres, "Los nombres son obligatorios");
        this.apellidos = validarTexto(apellidos, "Los apellidos son obligatorios");
        if (licenciaConducir == null) {
            throw new IllegalArgumentException("La licencia de conducir es obligatoria");
        }
        this.licenciaConducir = licenciaConducir;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public LicenciaConducir getLicenciaConducir() {
        return licenciaConducir;
    }

    public boolean validarLicencia() {
        return licenciaConducir.estaVigente();
    }

    public boolean estaHabilitado() {
        return validarLicencia();
    }

    public void actualizarDatos(String nombres, String apellidos) {
        this.nombres = validarTexto(nombres, "Los nombres son obligatorios");
        this.apellidos = validarTexto(apellidos, "Los apellidos son obligatorios");
    }

    private static String validarTexto(String valor, String mensaje) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(mensaje);
        }
        return valor.trim();
    }
}
