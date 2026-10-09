package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.infrastructure.adapters;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.util.List;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model.*;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.infrastructure.repositories.InspeccionTecnicaJpaRepository;
import org.junit.jupiter.api.Test;

class InspeccionTecnicaJpaAdapterTest {
    @Test void persisteYReconstruyeRangoMedicionResultadoYDefectos() {
        InspeccionTecnicaJpaRepository repository=mock(InspeccionTecnicaJpaRepository.class);
        when(repository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        InspeccionTecnicaJpaAdapter adapter=new InspeccionTecnicaJpaAdapter(repository);

        PruebaTecnica prueba=new PruebaTecnica(7L,new TipoPrueba("FRE","Frenos","%"),null,
                new RangoPermitido(10.0,20.0),ResultadoPrueba.PENDIENTE,List.of());
        InspeccionTecnica inspeccion=new InspeccionTecnica(3L,1L,2L,EstadoInspeccion.PENDIENTE,
                DictamenInspeccion.NO_CONCLUIDO,null,null,List.of(prueba));
        inspeccion.iniciar(); inspeccion.registrarMedicion(7L,15.0);
        inspeccion.registrarDefecto(7L,new DefectoDetectado(9L,"Desgaste",SeveridadDefecto.LEVE));

        InspeccionTecnica reconstruida=adapter.guardar(inspeccion);
        PruebaTecnica reconstruidaPrueba=reconstruida.getPruebas().getFirst();
        assertEquals(10.0,reconstruidaPrueba.getRangoPermitido().getMinimo());
        assertEquals(20.0,reconstruidaPrueba.getRangoPermitido().getMaximo());
        assertEquals(15.0,reconstruidaPrueba.getValorObtenido());
        assertEquals(ResultadoPrueba.APROBADO,reconstruidaPrueba.getResultado());
        assertEquals(SeveridadDefecto.LEVE,reconstruidaPrueba.getDefectos().getFirst().getSeveridad());
    }
}
