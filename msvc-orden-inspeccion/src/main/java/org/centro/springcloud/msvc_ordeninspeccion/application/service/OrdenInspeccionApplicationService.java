package org.centro.springcloud.msvc_ordeninspeccion.application.service;

import org.centro.springcloud.msvc_ordeninspeccion.domain.models.OrdenInspeccion;
import org.centro.springcloud.msvc_ordeninspeccion.domain.port.in.CancelarOrdenInspeccionUseCase;
import org.centro.springcloud.msvc_ordeninspeccion.domain.port.in.CrearOrdenInspeccionUseCase;
import org.centro.springcloud.msvc_ordeninspeccion.domain.port.in.HabilitarOrdenInspeccionUseCase;
import org.centro.springcloud.msvc_ordeninspeccion.domain.port.out.ClienteClientPort;
import org.centro.springcloud.msvc_ordeninspeccion.domain.port.out.OrdenInspeccionRepositoryPort;
import org.centro.springcloud.msvc_ordeninspeccion.domain.port.out.VehiculoClientPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenInspeccionApplicationService
        implements CrearOrdenInspeccionUseCase,
        HabilitarOrdenInspeccionUseCase,
        CancelarOrdenInspeccionUseCase {

    private final OrdenInspeccionRepositoryPort repositoryPort;
    private final ClienteClientPort clienteClientPort;
    private final VehiculoClientPort vehiculoClientPort;

    public OrdenInspeccionApplicationService(
            OrdenInspeccionRepositoryPort repositoryPort,
            ClienteClientPort clienteClientPort,
            VehiculoClientPort vehiculoClientPort) {

        this.repositoryPort = repositoryPort;
        this.clienteClientPort = clienteClientPort;
        this.vehiculoClientPort = vehiculoClientPort;
    }

    @Override
    public OrdenInspeccion crear(OrdenInspeccion orden) {

        // Verifica que el cliente exista.
        if (!clienteClientPort.existeCliente(orden.getClienteId())) {
            throw new RuntimeException(
                    "El cliente indicado no existe"
            );
        }

        // Verifica que el vehículo exista antes de crear la orden.
        if (!vehiculoClientPort.existeVehiculo(orden.getVehiculoId())) {
            throw new RuntimeException(
                    "El vehículo indicado no existe"
            );
        }

        return repositoryPort.guardar(orden);
    }

    @Override
    public OrdenInspeccion habilitar(Long id) {

        OrdenInspeccion orden = repositoryPort.buscarPorId(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Orden de inspección no encontrada"
                        ));

        orden.habilitar();

        return repositoryPort.guardar(orden);
    }

    @Override
    public OrdenInspeccion cancelar(Long id) {

        OrdenInspeccion orden = repositoryPort.buscarPorId(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Orden de inspección no encontrada"
                        ));

        orden.cancelar();

        return repositoryPort.guardar(orden);
    }

    // Métodos de consulta y actualización utilizados por el adaptador REST.

    public List<OrdenInspeccion> listar() {
        return repositoryPort.listar();
    }

    public Optional<OrdenInspeccion> buscarPorId(Long id) {
        return repositoryPort.buscarPorId(id);
    }

    public OrdenInspeccion actualizar(Long id,
                                      Long clienteId,
                                      Long vehiculoId,
                                      String tipo) {

        OrdenInspeccion orden = repositoryPort.buscarPorId(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Orden de inspección no encontrada"
                        ));

        if (!clienteClientPort.existeCliente(clienteId)) {
            throw new RuntimeException(
                    "El cliente indicado no existe"
            );
        }

        if (!vehiculoClientPort.existeVehiculo(vehiculoId)) {
            throw new RuntimeException(
                    "El vehículo indicado no existe"
            );
        }

        orden.actualizar(clienteId, vehiculoId, tipo);

        return repositoryPort.guardar(orden);
    }

    public void eliminar(Long id) {

        repositoryPort.buscarPorId(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Orden de inspección no encontrada"
                        ));

        repositoryPort.eliminar(id);
    }
}