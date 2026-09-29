package org.centro.msvc_vehiculo.domain.port.in;

import org.centro.msvc_vehiculo.domain.model.Vehiculo;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de entrada para las operaciones de consulta de vehículos.
 */
public interface ObtenerVehiculoPort {

    List<Vehiculo> obtenerTodos();

    Optional<Vehiculo> obtenerPorId(Long id);

    Optional<Vehiculo> obtenerPorPlaca(String placa);
}
