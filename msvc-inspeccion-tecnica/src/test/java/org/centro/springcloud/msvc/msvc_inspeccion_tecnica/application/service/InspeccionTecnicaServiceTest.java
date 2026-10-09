package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.application.service;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model.*;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.port.out.InspeccionTecnicaRepositoryPort;
import org.junit.jupiter.api.Test;

class InspeccionTecnicaServiceTest {
    @Test void crearInspeccionConOrdenValidadaYMismoVehiculo() {
        Fixture f=new Fixture(2L, true); InspeccionTecnica creada=f.service.crear(InspeccionTecnica.crear(1L,2L));
        assertNotNull(creada.getInspeccionId()); assertEquals(EstadoInspeccion.PENDIENTE,creada.getEstado());
    }
    @Test void rechazaOrdenInexistente() {
        Fixture f=new Fixture(null, false);
        assertThrows(IllegalArgumentException.class,()->f.service.crear(InspeccionTecnica.crear(1L,2L)));
    }
    @Test void rechazaOrdenPendiente() {
        Fixture f=new Fixture(2L, false);
        assertThrows(IllegalArgumentException.class,()->f.service.crear(InspeccionTecnica.crear(1L,2L)));
    }
    @Test void rechazaOrdenCancelada() {
        Fixture f=new Fixture(2L, false);
        assertThrows(IllegalArgumentException.class,()->f.service.crear(InspeccionTecnica.crear(1L,2L)));
    }
    @Test void rechazaOrdenValidadaConVehiculoDiferente() {
        Fixture f=new Fixture(3L, true);
        assertThrows(IllegalArgumentException.class,()->f.service.crear(InspeccionTecnica.crear(1L,2L)));
    }
    @Test void obtieneYListaInspeccion() {
        Fixture f=new Fixture(2L, true); Long id=f.service.crear(InspeccionTecnica.crear(1L,2L)).getInspeccionId();
        assertTrue(f.service.obtenerPorId(id).isPresent()); assertEquals(1,f.service.listar().size());
    }
    @Test void actualizaInspeccion() {
        Fixture f=new Fixture(2L, true); Long id=f.service.crear(InspeccionTecnica.crear(1L,2L)).getInspeccionId();
        InspeccionTecnica actualizada=f.service.actualizar(id,3L,2L).orElseThrow();
        assertEquals(3L,actualizada.getOrdenInspeccionId()); assertEquals(2L,actualizada.getVehiculoId());
    }
    @Test void eliminaInspeccion() {
        Fixture f=new Fixture(2L, true); Long id=f.service.crear(InspeccionTecnica.crear(1L,2L)).getInspeccionId();
        assertTrue(f.service.eliminar(id)); assertTrue(f.service.obtenerPorId(id).isEmpty()); assertFalse(f.service.eliminar(id));
    }
    private static class Fixture {
        final InspeccionTecnicaService service;
        Fixture(Long vehiculoId, boolean habilitada) {
            service=new InspeccionTecnicaService(new Repo(),id->vehiculoId == null
                    ? Optional.empty()
                    : Optional.of(new OrdenInspeccionConsultada(vehiculoId, habilitada)));
        }
    }
    private static class Repo implements InspeccionTecnicaRepositoryPort {
        private final Map<Long,InspeccionTecnica> datos=new LinkedHashMap<>(); private long secuencia;
        public InspeccionTecnica guardar(InspeccionTecnica i) {
            InspeccionTecnica g=i;
            if(i.getInspeccionId()==null) g=new InspeccionTecnica(++secuencia,i.getOrdenInspeccionId(),i.getVehiculoId(),
                    i.getEstado(),i.getDictamen(),i.getFechaInicio(),i.getFechaFin(),i.getPruebas());
            datos.put(g.getInspeccionId(),g); return g;
        }
        public List<InspeccionTecnica> listar(){return List.copyOf(datos.values());}
        public Optional<InspeccionTecnica> buscarPorId(Long id){return Optional.ofNullable(datos.get(id));}
        public void eliminar(Long id){datos.remove(id);}
    }
}
