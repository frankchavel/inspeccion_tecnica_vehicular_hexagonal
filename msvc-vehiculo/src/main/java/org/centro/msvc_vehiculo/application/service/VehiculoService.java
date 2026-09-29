package org.centro.msvc_vehiculo.application.service;

import org.centro.msvc_vehiculo.application.usecase.ActualizarVehiculoUseCase;
import org.centro.msvc_vehiculo.application.usecase.CrearVehiculoUseCase;
import org.centro.msvc_vehiculo.application.usecase.EliminarVehiculoUseCase;
import org.centro.msvc_vehiculo.application.usecase.ObtenerVehiculoUseCase;
import org.centro.msvc_vehiculo.domain.model.Vehiculo;
import org.centro.msvc_vehiculo.domain.port.in.ActualizarVehiculoPort;
import org.centro.msvc_vehiculo.domain.port.in.CrearVehiculoPort;
import org.centro.msvc_vehiculo.domain.port.in.EliminarVehiculoPort;
import org.centro.msvc_vehiculo.domain.port.in.ObtenerVehiculoPort;
import org.centro.msvc_vehiculo.domain.port.out.VehiculoRepositoryPort;

import java.util.List;
import java.util.Optional;

/**
 * Implementación pura de los casos de uso y puertos de entrada.
 * No tiene dependencias directas de JPA, @Entity ni repositorios de Spring Data.
 * Recibe VehiculoRepositoryPort por constructor (inyectado desde ApplicationConfig).
 */
public class VehiculoService implements
        CrearVehiculoUseCase,
        ObtenerVehiculoUseCase,
        ActualizarVehiculoUseCase,
        EliminarVehiculoUseCase,
        CrearVehiculoPort,
        ObtenerVehiculoPort,
        ActualizarVehiculoPort,
        EliminarVehiculoPort {

    private final VehiculoRepositoryPort repositoryPort;

    public VehiculoService(VehiculoRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    // ---- CrearVehiculoUseCase / CrearVehiculoPort ----

    @Override
    public Vehiculo crear(Vehiculo vehiculo) {
        return repositoryPort.save(vehiculo);
    }

    // ---- ObtenerVehiculoUseCase / ObtenerVehiculoPort ----

    @Override
    public List<Vehiculo> obtenerTodos() {
        return repositoryPort.findAll();
    }

    @Override
    public Optional<Vehiculo> obtenerPorId(Long id) {
        return repositoryPort.findById(id);
    }

    @Override
    public Optional<Vehiculo> obtenerPorPlaca(String placa) {
        return repositoryPort.findByPlaca(placa);
    }

    // ---- ActualizarVehiculoUseCase / ActualizarVehiculoPort ----

    @Override
    public Optional<Vehiculo> actualizar(Long id, Vehiculo vehiculo) {
        return repositoryPort.findById(id).map(existente -> {
            existente.setPlaca(vehiculo.getPlaca());
            existente.setMarca(vehiculo.getMarca());
            existente.setModelo(vehiculo.getModelo());
            existente.setAnioFabricacion(vehiculo.getAnioFabricacion());
            return repositoryPort.save(existente);
        });
    }

    // ---- EliminarVehiculoUseCase / EliminarVehiculoPort ----

    @Override
    public boolean eliminar(Long id) {
        if (!repositoryPort.existsById(id)) {
            return false;
        }
        repositoryPort.deleteById(id);
        return true;
    }
}
