package org.centro.msvc_vehiculo.application.usecase;

import org.centro.msvc_vehiculo.domain.model.Vehiculo;

import java.util.List;
import java.util.Optional;

/**
 * Caso de uso para consultar vehículos.
 */
public interface ObtenerVehiculoUseCase {

    List<Vehiculo> obtenerTodos();

    Optional<Vehiculo> obtenerPorId(Long id);

    Optional<Vehiculo> obtenerPorPlaca(String placa);
}
