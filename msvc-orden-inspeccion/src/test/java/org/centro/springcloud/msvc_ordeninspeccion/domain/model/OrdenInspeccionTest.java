package org.centro.springcloud.msvc_ordeninspeccion.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OrdenInspeccionTest {
    @Test
    void iniciaPendienteConFechaAutomatica() {
        OrdenInspeccion orden = OrdenInspeccion.crear(1L, 2L, TipoOrden.REVISION_TECNICA);
        assertEquals(EstadoOrden.PENDIENTE, orden.getEstado());
        assertNotNull(orden.getFechaCreacion());
        assertFalse(orden.estaHabilitada());
    }

    @Test
    void validarHabilitaLaOrden() {
        OrdenInspeccion orden = OrdenInspeccion.crear(1L, 2L, TipoOrden.OTROS);
        orden.validar();
        assertEquals(EstadoOrden.VALIDADA, orden.getEstado());
        assertTrue(orden.estaHabilitada());
    }

    @Test
    void cancelarDejaLaOrdenNoHabilitada() {
        OrdenInspeccion orden = OrdenInspeccion.crear(1L, 2L, TipoOrden.EMISIONES_CONTAMINANTES);
        orden.cancelar();
        assertEquals(EstadoOrden.CANCELADA, orden.getEstado());
        assertFalse(orden.estaHabilitada());
    }

    @Test
    void exigeLosDatosObligatorios() {
        assertThrows(IllegalArgumentException.class,
                () -> OrdenInspeccion.crear(null, 2L, TipoOrden.OTROS));
        assertThrows(IllegalArgumentException.class,
                () -> OrdenInspeccion.crear(1L, null, TipoOrden.OTROS));
        assertThrows(IllegalArgumentException.class,
                () -> OrdenInspeccion.crear(1L, 2L, null));
    }
}
