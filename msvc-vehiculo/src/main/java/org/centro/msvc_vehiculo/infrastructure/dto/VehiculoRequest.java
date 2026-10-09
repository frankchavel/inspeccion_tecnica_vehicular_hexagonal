package org.centro.msvc_vehiculo.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import org.centro.msvc_vehiculo.domain.model.Soat;
import org.centro.msvc_vehiculo.domain.model.TituloPropiedad;
import org.centro.msvc_vehiculo.domain.model.Vehiculo;

public record VehiculoRequest(
        @NotBlank(message = "La placa es obligatoria")
        String placa,
        @NotBlank(message = "La marca es obligatoria")
        String marca,
        @NotBlank(message = "El modelo es obligatorio")
        String modelo,
        @NotNull(message = "El año de fabricación es obligatorio")
        Integer anioFabricacion,
        @NotBlank(message = "El número de SOAT es obligatorio")
        String numeroSoat,
        @NotNull(message = "La fecha de vencimiento del SOAT es obligatoria")
        LocalDate fechaVencimientoSoat,
        @NotBlank(message = "El número de título de propiedad es obligatorio")
        String numeroTituloPropiedad,
        @NotNull(message = "La validez del título de propiedad es obligatoria")
        Boolean tituloValido
) {
    public Vehiculo toDomain() {
        return new Vehiculo(
                null,
                placa,
                marca,
                modelo,
                anioFabricacion,
                new Soat(numeroSoat, fechaVencimientoSoat),
                new TituloPropiedad(numeroTituloPropiedad, tituloValido)
        );
    }
}
