package org.centro.msvc_vehiculo.domain.port.in;

import org.centro.msvc_vehiculo.domain.model.Vehiculo;

import java.util.Optional;

/**
 * Puerto de entrada para la operación de actualización de vehículos.
 */
public interface ActualizarVehiculoPort {

    Optional<Vehiculo> actualizar(Long id, Vehiculo vehiculo);
}
