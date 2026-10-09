package org.centro.msvc_vehiculo.application.service;

import org.centro.msvc_vehiculo.application.usecase.ActualizarVehiculoUseCase;
import org.centro.msvc_vehiculo.application.usecase.CrearVehiculoUseCase;
import org.centro.msvc_vehiculo.application.usecase.EliminarVehiculoUseCase;
import org.centro.msvc_vehiculo.application.usecase.ObtenerVehiculoUseCase;
import org.centro.msvc_vehiculo.domain.model.Vehiculo;
import org.centro.msvc_vehiculo.domain.port.out.VehiculoRepositoryPort;

import java.util.List;
import java.util.Optional;

public class VehiculoService implements
        CrearVehiculoUseCase,
        ObtenerVehiculoUseCase,
        ActualizarVehiculoUseCase,
        EliminarVehiculoUseCase {

    private final VehiculoRepositoryPort repositoryPort;

    public VehiculoService(VehiculoRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Vehiculo crear(Vehiculo vehiculo) {
        if (repositoryPort.existePlaca(vehiculo.getPlaca())) {
            throw new IllegalArgumentException("Ya existe un vehículo registrado con la placa indicada");
        }
        return repositoryPort.guardar(vehiculo);
    }

    @Override
    public List<Vehiculo> obtenerTodos() {
        return repositoryPort.listar();
    }

    @Override
    public Optional<Vehiculo> obtenerPorId(Long id) {
        return repositoryPort.buscarPorId(id);
    }

    @Override
    public Optional<Vehiculo> actualizar(Long id, Vehiculo vehiculo) {
        return repositoryPort.buscarPorId(id).map(existente -> {
            existente.actualizarDatos(
                    vehiculo.getMarca(),
                    vehiculo.getModelo(),
                    vehiculo.getAnioFabricacion()
            );
            return repositoryPort.guardar(existente);
        });
    }

    @Override
    public boolean eliminar(Long id) {
        if (repositoryPort.buscarPorId(id).isEmpty()) {
            return false;
        }
        repositoryPort.eliminar(id);
        return true;
    }
}
