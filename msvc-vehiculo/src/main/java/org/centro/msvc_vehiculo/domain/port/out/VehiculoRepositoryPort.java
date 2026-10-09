package org.centro.msvc_vehiculo.domain.port.out;

import org.centro.msvc_vehiculo.domain.model.Vehiculo;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida hacia la capa de persistencia.
 * El dominio depende de esta abstracción, no de la implementación concreta.
 */
public interface VehiculoRepositoryPort {

    Vehiculo guardar(Vehiculo vehiculo);

    Optional<Vehiculo> buscarPorId(Long id);

    List<Vehiculo> listar();

    void eliminar(Long id);

    boolean existePlaca(String placa);
}
