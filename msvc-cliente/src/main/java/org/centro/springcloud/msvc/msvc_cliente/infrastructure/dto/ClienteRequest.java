package org.centro.springcloud.msvc.msvc_cliente.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import org.centro.springcloud.msvc.msvc_cliente.domain.model.Cliente;
import org.centro.springcloud.msvc.msvc_cliente.domain.model.LicenciaConducir;

public record ClienteRequest(
        @NotBlank(message = "El documento de identidad es obligatorio")
        String documentoIdentidad,
        @NotBlank(message = "Los nombres son obligatorios")
        String nombres,
        @NotBlank(message = "Los apellidos son obligatorios")
        String apellidos,
        @NotBlank(message = "El número de licencia es obligatorio")
        String numeroLicencia,
        @NotNull(message = "La fecha de vencimiento de la licencia es obligatoria")
        LocalDate fechaVencimientoLicencia
) {
    public Cliente toDomain() {
        return new Cliente(
                null,
                documentoIdentidad,
                nombres,
                apellidos,
                new LicenciaConducir(numeroLicencia, fechaVencimientoLicencia)
        );
    }
}
