package org.centro.msvc_vehiculo.application.usecase;

import org.centro.msvc_vehiculo.domain.model.Vehiculo;

import java.util.Optional;

/**
 * Caso de uso para actualizar un vehículo.
 */
public interface ActualizarVehiculoUseCase {

    Optional<Vehiculo> actualizar(Long id, Vehiculo vehiculo);
}
