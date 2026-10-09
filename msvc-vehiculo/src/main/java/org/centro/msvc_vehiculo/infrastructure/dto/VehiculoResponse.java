package org.centro.msvc_vehiculo.infrastructure.dto;

import java.time.LocalDate;
import org.centro.msvc_vehiculo.domain.model.Vehiculo;

public record VehiculoResponse(
        Long vehiculoId,
        String placa,
        String marca,
        String modelo,
        Integer anioFabricacion,
        String numeroSoat,
        LocalDate fechaVencimientoSoat,
        String numeroTituloPropiedad,
        Boolean tituloValido,
        Boolean habilitado
) {
    public static VehiculoResponse fromDomain(Vehiculo vehiculo) {
        return new VehiculoResponse(
                vehiculo.getVehiculoId(),
                vehiculo.getPlaca(),
                vehiculo.getMarca(),
                vehiculo.getModelo(),
                vehiculo.getAnioFabricacion(),
                vehiculo.getSoat().getNumero(),
                vehiculo.getSoat().getFechaVencimiento(),
                vehiculo.getTituloPropiedad().getNumero(),
                vehiculo.getTituloPropiedad().getValido(),
                vehiculo.estaHabilitado()
        );
    }
}
