package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.application.service;

import java.util.List;
import java.util.Optional;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.application.usecase.*;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model.InspeccionTecnica;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model.OrdenInspeccionConsultada;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.port.out.InspeccionTecnicaRepositoryPort;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.port.out.OrdenInspeccionPort;

public class InspeccionTecnicaService implements CrearInspeccionTecnicaUseCase,
        ObtenerInspeccionTecnicaUseCase, ActualizarInspeccionTecnicaUseCase,
        EliminarInspeccionTecnicaUseCase, AgregarPruebaUseCase,
        IniciarInspeccionUseCase, RegistrarResultadoPruebaUseCase,
        RegistrarDefectoUseCase, FinalizarInspeccionUseCase {
    private final InspeccionTecnicaRepositoryPort repository;
    private final OrdenInspeccionPort ordenPort;

    public InspeccionTecnicaService(InspeccionTecnicaRepositoryPort repository, OrdenInspeccionPort ordenPort) {
        this.repository = repository;
        this.ordenPort = ordenPort;
    }
    @Override public InspeccionTecnica crear(InspeccionTecnica inspeccion) {
        validarOrden(inspeccion.getOrdenInspeccionId(), inspeccion.getVehiculoId());
        return repository.guardar(inspeccion);
    }
    @Override public List<InspeccionTecnica> listar() { return repository.listar(); }
    @Override public Optional<InspeccionTecnica> obtenerPorId(Long id) { return repository.buscarPorId(id); }
    @Override public Optional<InspeccionTecnica> actualizar(Long id, Long ordenId, Long vehiculoId) {
        Optional<InspeccionTecnica> encontrada = repository.buscarPorId(id);
        if (encontrada.isEmpty()) return Optional.empty();
        validarOrden(ordenId, vehiculoId);
        encontrada.get().actualizarDatos(ordenId, vehiculoId);
        return Optional.of(repository.guardar(encontrada.get()));
    }
    @Override public boolean eliminar(Long id) {
        if (repository.buscarPorId(id).isEmpty()) return false;
        repository.eliminar(id);
        return true;
    }

    @Override public Optional<InspeccionTecnica> agregarPrueba(Long id,
            org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model.PruebaTecnica prueba) {
        return cambiar(id, inspeccion -> inspeccion.agregarPrueba(prueba));
    }
    @Override public Optional<InspeccionTecnica> iniciar(Long id) {
        return cambiar(id, InspeccionTecnica::iniciar);
    }
    @Override public Optional<InspeccionTecnica> registrarMedicion(Long id, Long pruebaId, Double valor) {
        return cambiar(id, inspeccion -> inspeccion.registrarMedicion(pruebaId, valor));
    }
    @Override public Optional<InspeccionTecnica> registrarDefecto(Long id, Long pruebaId,
            org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model.DefectoDetectado defecto) {
        return cambiar(id, inspeccion -> inspeccion.registrarDefecto(pruebaId, defecto));
    }
    @Override public Optional<InspeccionTecnica> finalizar(Long id) {
        return cambiar(id, InspeccionTecnica::finalizar);
    }
    private Optional<InspeccionTecnica> cambiar(Long id, Accion accion) {
        Optional<InspeccionTecnica> encontrada = repository.buscarPorId(id);
        if (encontrada.isEmpty()) return Optional.empty();
        accion.ejecutar(encontrada.get());
        return Optional.of(repository.guardar(encontrada.get()));
    }
    private void validarOrden(Long id, Long vehiculoId) {
        if (id == null) throw new IllegalArgumentException("La orden de inspeccion es obligatoria");
        OrdenInspeccionConsultada orden = ordenPort.obtenerPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Orden de inspeccion no encontrada"));
        if (!orden.isHabilitada()) {
            throw new IllegalArgumentException("La orden de inspeccion no esta validada");
        }
        if (!orden.getVehiculoId().equals(vehiculoId)) {
            throw new IllegalArgumentException("El vehiculo no corresponde a la orden de inspeccion");
        }
    }
    @FunctionalInterface private interface Accion { void ejecutar(InspeccionTecnica inspeccion); }
}
