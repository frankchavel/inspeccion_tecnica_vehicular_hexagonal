package org.centro.msvc_vehiculo.domain.port.in;

import org.centro.msvc_vehiculo.domain.model.Vehiculo;

/**
 * Puerto de entrada para la operación de creación de vehículos.
 */
public interface CrearVehiculoPort {

    Vehiculo crear(Vehiculo vehiculo);
}
