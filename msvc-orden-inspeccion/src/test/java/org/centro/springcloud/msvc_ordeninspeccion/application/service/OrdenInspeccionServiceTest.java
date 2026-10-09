package org.centro.springcloud.msvc_ordeninspeccion.application.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.*;
import org.centro.springcloud.msvc_ordeninspeccion.domain.model.*;
import org.centro.springcloud.msvc_ordeninspeccion.domain.port.out.*;
import org.junit.jupiter.api.Test;

class OrdenInspeccionServiceTest {
    @Test
    void creaOrdenValida() {
        Fixture fixture = new Fixture(Optional.of(true), Optional.of(true));
        OrdenInspeccion creada = fixture.service.crear(nuevaOrden());
        assertNotNull(creada.getOrdenInspeccionId());
        assertEquals(EstadoOrden.PENDIENTE, creada.getEstado());
    }

    @Test
    void validaYPersisteOrdenPendiente() {
        Fixture fixture = new Fixture(Optional.of(true), Optional.of(true));
        OrdenInspeccion creada = fixture.service.crear(nuevaOrden());

        OrdenInspeccion validada = fixture.service.validar(creada.getOrdenInspeccionId());

        assertEquals(EstadoOrden.VALIDADA, validada.getEstado());
        assertEquals(EstadoOrden.VALIDADA, fixture.service.obtenerPorId(creada.getOrdenInspeccionId())
                .orElseThrow().getEstado());
    }

    @Test
    void rechazaClienteInexistente() {
        Fixture fixture = new Fixture(Optional.empty(), Optional.of(true));
        RuntimeException error = assertThrows(RuntimeException.class,
                () -> fixture.service.crear(nuevaOrden()));
        assertEquals("Cliente no encontrado", error.getMessage());
    }

    @Test
    void rechazaVehiculoInexistente() {
        Fixture fixture = new Fixture(Optional.of(true), Optional.empty());
        RuntimeException error = assertThrows(RuntimeException.class,
                () -> fixture.service.crear(nuevaOrden()));
        assertEquals("Vehiculo no encontrado", error.getMessage());
    }

    @Test
    void rechazaClienteNoHabilitado() {
        Fixture fixture = new Fixture(Optional.of(false), Optional.of(true));
        RuntimeException error = assertThrows(RuntimeException.class,
                () -> fixture.service.crear(nuevaOrden()));
        assertEquals("El cliente no tiene licencia vigente", error.getMessage());
    }

    @Test
    void rechazaVehiculoNoHabilitado() {
        Fixture fixture = new Fixture(Optional.of(true), Optional.of(false));
        RuntimeException error = assertThrows(RuntimeException.class,
                () -> fixture.service.crear(nuevaOrden()));
        assertEquals("El vehiculo no cumple requisitos para inspeccion", error.getMessage());
    }

    @Test
    void permiteObtenerYListar() {
        Fixture fixture = new Fixture(Optional.of(true), Optional.of(true));
        OrdenInspeccion creada = fixture.service.crear(nuevaOrden());
        assertEquals(creada.getOrdenInspeccionId(),
                fixture.service.obtenerPorId(creada.getOrdenInspeccionId()).orElseThrow().getOrdenInspeccionId());
        assertEquals(1, fixture.service.listar().size());
    }

    @Test
    void permiteActualizar() {
        Fixture fixture = new Fixture(Optional.of(true), Optional.of(true));
        OrdenInspeccion creada = fixture.service.crear(nuevaOrden());
        OrdenInspeccion actualizada = fixture.service.actualizar(
                creada.getOrdenInspeccionId(), 10L, 20L, TipoOrden.OTROS);
        assertEquals(10L, actualizada.getClienteId());
        assertEquals(20L, actualizada.getVehiculoId());
        assertEquals(TipoOrden.OTROS, actualizada.getTipoOrden());
    }

    @Test
    void permiteEliminarYReportaAusencia() {
        Fixture fixture = new Fixture(Optional.of(true), Optional.of(true));
        Long id = fixture.service.crear(nuevaOrden()).getOrdenInspeccionId();
        fixture.service.eliminar(id);
        assertTrue(fixture.service.obtenerPorId(id).isEmpty());
        assertThrows(NoSuchElementException.class, () -> fixture.service.eliminar(id));
    }

    private static OrdenInspeccion nuevaOrden() {
        return OrdenInspeccion.crear(1L, 2L, TipoOrden.REVISION_TECNICA);
    }

    private static final class Fixture {
        private final OrdenInspeccionService service;

        private Fixture(Optional<Boolean> cliente, Optional<Boolean> vehiculo) {
            this.service = new OrdenInspeccionService(new InMemoryRepository(), id -> cliente, id -> vehiculo);
        }
    }

    private static final class InMemoryRepository implements OrdenInspeccionRepositoryPort {
        private final Map<Long, OrdenInspeccion> datos = new LinkedHashMap<>();
        private long secuencia;

        @Override
        public List<OrdenInspeccion> listar() { return List.copyOf(datos.values()); }

        @Override
        public Optional<OrdenInspeccion> buscarPorId(Long id) { return Optional.ofNullable(datos.get(id)); }

        @Override
        public OrdenInspeccion guardar(OrdenInspeccion orden) {
            OrdenInspeccion guardada = orden;
            if (orden.getOrdenInspeccionId() == null) {
                guardada = new OrdenInspeccion(++secuencia, orden.getClienteId(), orden.getVehiculoId(),
                        orden.getTipoOrden(), orden.getEstado(), orden.getFechaCreacion());
            }
            datos.put(guardada.getOrdenInspeccionId(), guardada);
            return guardada;
        }

        @Override
        public void eliminar(Long id) { datos.remove(id); }
    }
}
