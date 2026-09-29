package org.centro.msvc_vehiculo.domain.port.out;

import org.centro.msvc_vehiculo.domain.model.Vehiculo;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida hacia la capa de persistencia.
 * El dominio depende de esta abstracción, no de la implementación concreta.
 */
public interface VehiculoRepositoryPort {

    Vehiculo save(Vehiculo vehiculo);

    Optional<Vehiculo> findById(Long id);

    List<Vehiculo> findAll();

    Optional<Vehiculo> findByPlaca(String placa);

    void deleteById(Long id);

    boolean existsById(Long id);
}
