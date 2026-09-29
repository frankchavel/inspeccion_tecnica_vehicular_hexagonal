package org.centro.msvc_vehiculo.application.usecase;

import org.centro.msvc_vehiculo.domain.model.Vehiculo;

/**
 * Caso de uso para crear un vehículo.
 */
public interface CrearVehiculoUseCase {

    Vehiculo crear(Vehiculo vehiculo);
}
