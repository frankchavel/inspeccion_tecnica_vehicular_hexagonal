package org.centro.springcloud.msvc.msvc_cliente.infrastructure.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteId;

    @Column(name = "documento_identidad", nullable = false)
    private String documentoIdentidad;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(name = "numero_licencia", nullable = false)
    private String numeroLicencia;

    @Column(name = "fecha_vencimiento_licencia", nullable = false)
    private LocalDate fechaVencimientoLicencia;

    public ClienteEntity() {
    }

    public ClienteEntity(Long clienteId, String documentoIdentidad, String nombres,
            String apellidos, String numeroLicencia, LocalDate fechaVencimientoLicencia) {
        this.clienteId = clienteId;
        this.documentoIdentidad = documentoIdentidad;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroLicencia = numeroLicencia;
        this.fechaVencimientoLicencia = fechaVencimientoLicencia;
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

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public LocalDate getFechaVencimientoLicencia() {
        return fechaVencimientoLicencia;
    }
}
