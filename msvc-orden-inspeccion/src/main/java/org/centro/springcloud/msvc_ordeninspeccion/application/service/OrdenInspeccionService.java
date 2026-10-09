package org.centro.springcloud.msvc_ordeninspeccion.application.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.centro.springcloud.msvc_ordeninspeccion.application.usecase.ActualizarOrdenInspeccionUseCase;
import org.centro.springcloud.msvc_ordeninspeccion.application.usecase.CrearOrdenInspeccionUseCase;
import org.centro.springcloud.msvc_ordeninspeccion.application.usecase.EliminarOrdenInspeccionUseCase;
import org.centro.springcloud.msvc_ordeninspeccion.application.usecase.ObtenerOrdenInspeccionUseCase;
import org.centro.springcloud.msvc_ordeninspeccion.application.usecase.ValidarOrdenInspeccionUseCase;
import org.centro.springcloud.msvc_ordeninspeccion.domain.model.OrdenInspeccion;
import org.centro.springcloud.msvc_ordeninspeccion.domain.model.TipoOrden;
import org.centro.springcloud.msvc_ordeninspeccion.domain.port.out.ClientePort;
import org.centro.springcloud.msvc_ordeninspeccion.domain.port.out.OrdenInspeccionRepositoryPort;
import org.centro.springcloud.msvc_ordeninspeccion.domain.port.out.VehiculoPort;

public class OrdenInspeccionService implements CrearOrdenInspeccionUseCase,
        ObtenerOrdenInspeccionUseCase, ActualizarOrdenInspeccionUseCase,
        EliminarOrdenInspeccionUseCase, ValidarOrdenInspeccionUseCase {

    private final OrdenInspeccionRepositoryPort repositoryPort;
    private final ClientePort clientePort;
    private final VehiculoPort vehiculoPort;

    public OrdenInspeccionService(OrdenInspeccionRepositoryPort repositoryPort,
                                  ClientePort clientePort,
                                  VehiculoPort vehiculoPort) {
        this.repositoryPort = repositoryPort;
        this.clientePort = clientePort;
        this.vehiculoPort = vehiculoPort;
    }

    @Override
    public OrdenInspeccion crear(OrdenInspeccion orden) {
        validarClienteYVehiculo(orden.getClienteId(), orden.getVehiculoId());
        return repositoryPort.guardar(orden);
    }

    @Override
    public List<OrdenInspeccion> listar() {
        return repositoryPort.listar();
    }

    @Override
    public Optional<OrdenInspeccion> obtenerPorId(Long id) {
        return repositoryPort.buscarPorId(id);
    }

    @Override
    public OrdenInspeccion actualizar(Long id, Long clienteId, Long vehiculoId, TipoOrden tipoOrden) {
        OrdenInspeccion orden = repositoryPort.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("Orden de inspeccion no encontrada: " + id));
        validarClienteYVehiculo(clienteId, vehiculoId);
        orden.actualizarDatos(clienteId, vehiculoId, tipoOrden);
        return repositoryPort.guardar(orden);
    }

    @Override
    public void eliminar(Long id) {
        if (repositoryPort.buscarPorId(id).isEmpty()) {
            throw new NoSuchElementException("Orden de inspeccion no encontrada: " + id);
        }
        repositoryPort.eliminar(id);
    }

    @Override
    public OrdenInspeccion validar(Long id) {
        OrdenInspeccion orden = repositoryPort.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("Orden de inspeccion no encontrada: " + id));
        orden.validar();
        return repositoryPort.guardar(orden);
    }

    private void validarClienteYVehiculo(Long clienteId, Long vehiculoId) {
        Boolean clienteHabilitado = clientePort.obtenerHabilitacion(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        if (!Boolean.TRUE.equals(clienteHabilitado)) {
            throw new RuntimeException("El cliente no tiene licencia vigente");
        }

        Boolean vehiculoHabilitado = vehiculoPort.obtenerHabilitacion(vehiculoId)
                .orElseThrow(() -> new RuntimeException("Vehiculo no encontrado"));
        if (!Boolean.TRUE.equals(vehiculoHabilitado)) {
            throw new RuntimeException("El vehiculo no cumple requisitos para inspeccion");
        }
    }
}
