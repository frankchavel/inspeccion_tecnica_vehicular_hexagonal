package org.centro.springcloud.msvc.msvc_cliente.infrastructure.dto;

import java.time.LocalDate;
import org.centro.springcloud.msvc.msvc_cliente.domain.model.Cliente;

public record ClienteResponse(
        Long clienteId,
        String documentoIdentidad,
        String nombres,
        String apellidos,
        String numeroLicencia,
        LocalDate fechaVencimientoLicencia,
        boolean habilitado
) {
    public static ClienteResponse fromDomain(Cliente cliente) {
        return new ClienteResponse(
                cliente.getClienteId(),
                cliente.getDocumentoIdentidad(),
                cliente.getNombres(),
                cliente.getApellidos(),
                cliente.getLicenciaConducir().getNumero(),
                cliente.getLicenciaConducir().getFechaVencimiento(),
                cliente.estaHabilitado()
        );
    }
}
