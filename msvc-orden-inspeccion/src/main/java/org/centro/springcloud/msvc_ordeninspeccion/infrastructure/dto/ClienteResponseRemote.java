package org.centro.springcloud.msvc_ordeninspeccion.infrastructure.dto;

public class ClienteResponseRemote {
    private Long clienteId;
    private String documentoIdentidad;
    private String nombres;
    private String apellidos;
    private Boolean habilitado;

    public ClienteResponseRemote() { }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public String getDocumentoIdentidad() { return documentoIdentidad; }
    public void setDocumentoIdentidad(String documentoIdentidad) { this.documentoIdentidad = documentoIdentidad; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public Boolean getHabilitado() { return habilitado; }
    public void setHabilitado(Boolean habilitado) { this.habilitado = habilitado; }
}
