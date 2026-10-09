package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;

class InspeccionTecnicaTest {
    private PruebaTecnica pendiente(Long id, double minimo, double maximo) {
        return new PruebaTecnica(id, new TipoPrueba("FRE", "Frenos", "%"), null,
                new RangoPermitido(minimo, maximo), ResultadoPrueba.PENDIENTE, List.of());
    }
    private InspeccionTecnica iniciadaCon(PruebaTecnica prueba) {
        InspeccionTecnica inspeccion=InspeccionTecnica.crear(1L,2L);
        inspeccion.agregarPrueba(prueba); inspeccion.iniciar(); return inspeccion;
    }
    @Test void agregaPruebaEInicia() {
        InspeccionTecnica i=iniciadaCon(pendiente(1L,10,20));
        assertEquals(1,i.getPruebas().size()); assertEquals(EstadoInspeccion.EN_PROCESO,i.getEstado());
        assertNotNull(i.getFechaInicio());
    }
    @Test void valorDentroDelRangoAprueba() {
        PruebaTecnica p=pendiente(1L,10,20); p.registrarMedicion(15.0);
        assertEquals(ResultadoPrueba.APROBADO,p.getResultado());
    }
    @Test void limitesDelRangoAprueban() {
        PruebaTecnica minimo=pendiente(1L,10,20); minimo.registrarMedicion(10.0);
        PruebaTecnica maximo=pendiente(2L,10,20); maximo.registrarMedicion(20.0);
        assertEquals(ResultadoPrueba.APROBADO,minimo.getResultado());
        assertEquals(ResultadoPrueba.APROBADO,maximo.getResultado());
    }
    @Test void valoresFueraDelRangoDesaprueban() {
        PruebaTecnica menor=pendiente(1L,10,20); menor.registrarMedicion(9.99);
        PruebaTecnica mayor=pendiente(2L,10,20); mayor.registrarMedicion(20.01);
        assertEquals(ResultadoPrueba.DESAPROBADO,menor.getResultado());
        assertEquals(ResultadoPrueba.DESAPROBADO,mayor.getResultado());
    }
    @Test void finalizaAprobadaCuandoTodasAprueban() {
        InspeccionTecnica i=iniciadaCon(pendiente(1L,10,20)); i.registrarMedicion(1L,15.0); i.finalizar();
        assertEquals(DictamenInspeccion.APROBADO,i.getDictamen()); assertTrue(i.estaFinalizada());
    }
    @Test void finalizaDesaprobadaCuandoAlgunaDesaprueba() {
        InspeccionTecnica i=InspeccionTecnica.crear(1L,2L);
        i.agregarPrueba(pendiente(1L,10,20)); i.agregarPrueba(pendiente(2L,0,5)); i.iniciar();
        i.registrarMedicion(1L,15.0); i.registrarMedicion(2L,8.0); i.finalizar();
        assertEquals(DictamenInspeccion.DESAPROBADO,i.getDictamen());
    }
    @Test void noFinalizaSinPruebasNiConPendientesNiAntesDeIniciar() {
        InspeccionTecnica sinPruebas=InspeccionTecnica.crear(1L,2L); sinPruebas.iniciar();
        assertThrows(IllegalStateException.class,sinPruebas::finalizar);
        InspeccionTecnica pendiente=iniciadaCon(pendiente(1L,10,20));
        assertThrows(IllegalStateException.class,pendiente::finalizar);
        InspeccionTecnica noIniciada=InspeccionTecnica.crear(1L,2L); noIniciada.agregarPrueba(pendiente(2L,1,2));
        assertThrows(IllegalStateException.class,noIniciada::finalizar);
    }
    @Test void registraDefectoSinInventarResultado() {
        InspeccionTecnica i=iniciadaCon(pendiente(1L,10,20));
        i.registrarDefecto(1L,new DefectoDetectado(null,"Desgaste",SeveridadDefecto.GRAVE));
        assertEquals(1,i.getPruebas().getFirst().getDefectos().size());
        assertEquals(ResultadoPrueba.PENDIENTE,i.getPruebas().getFirst().getResultado());
    }
    @Test void impideModificarFinalizada() {
        InspeccionTecnica i=iniciadaCon(pendiente(1L,10,20)); i.registrarMedicion(1L,15.0); i.finalizar();
        assertThrows(IllegalStateException.class,()->i.actualizarDatos(2L,3L));
        assertThrows(IllegalStateException.class,()->i.agregarPrueba(pendiente(2L,1,2)));
        assertThrows(IllegalStateException.class,()->i.registrarMedicion(1L,12.0));
    }
}
