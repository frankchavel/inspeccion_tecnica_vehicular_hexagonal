package org.centro.msvc_vehiculo.domain.port.in;

/**
 * Puerto de entrada para la operación de eliminación de vehículos.
 */
public interface EliminarVehiculoPort {

    boolean eliminar(Long id);
}
