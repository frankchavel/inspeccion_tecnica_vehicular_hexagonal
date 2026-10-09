package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.infrastructure.adapters;

import java.util.List;
import java.util.Optional;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model.*;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.port.out.InspeccionTecnicaRepositoryPort;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.infrastructure.entities.*;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.infrastructure.repositories.InspeccionTecnicaJpaRepository;

public class InspeccionTecnicaJpaAdapter implements InspeccionTecnicaRepositoryPort {
    private final InspeccionTecnicaJpaRepository repository;
    public InspeccionTecnicaJpaAdapter(InspeccionTecnicaJpaRepository repository) { this.repository = repository; }
    @Override public InspeccionTecnica guardar(InspeccionTecnica inspeccion) { return toDomain(repository.save(toEntity(inspeccion))); }
    @Override public List<InspeccionTecnica> listar() { return repository.findAll().stream().map(this::toDomain).toList(); }
    @Override public Optional<InspeccionTecnica> buscarPorId(Long id) { return repository.findById(id).map(this::toDomain); }
    @Override public void eliminar(Long id) { repository.deleteById(id); }

    private InspeccionTecnica toDomain(InspeccionTecnicaEntity e) {
        List<PruebaTecnica> pruebas = e.getPruebas().stream().map(this::toDomain).toList();
        return new InspeccionTecnica(e.getInspeccionId(), e.getOrdenInspeccionId(), e.getVehiculoId(),
                e.getEstado(), e.getDictamen(), e.getFechaInicio(), e.getFechaFin(), pruebas);
    }
    private PruebaTecnica toDomain(PruebaTecnicaEntity e) {
        List<DefectoDetectado> defectos = e.getDefectos().stream()
                .map(d -> new DefectoDetectado(d.getDefectoId(), d.getDescripcion(), d.getSeveridad())).toList();
        return new PruebaTecnica(e.getPruebaId(),
                new TipoPrueba(e.getCodigo(), e.getDescripcion(), e.getUnidadMedida()), e.getValorObtenido(),
                new RangoPermitido(e.getMinimo(), e.getMaximo()), e.getResultado(), defectos);
    }
    private InspeccionTecnicaEntity toEntity(InspeccionTecnica d) {
        InspeccionTecnicaEntity e = new InspeccionTecnicaEntity();
        e.setInspeccionId(d.getInspeccionId()); e.setOrdenInspeccionId(d.getOrdenInspeccionId());
        e.setVehiculoId(d.getVehiculoId()); e.setEstado(d.getEstado()); e.setDictamen(d.getDictamen());
        e.setFechaInicio(d.getFechaInicio()); e.setFechaFin(d.getFechaFin());
        e.setPruebas(d.getPruebas().stream().map(this::toEntity).toList());
        return e;
    }
    private PruebaTecnicaEntity toEntity(PruebaTecnica d) {
        PruebaTecnicaEntity e = new PruebaTecnicaEntity();
        e.setPruebaId(d.getPruebaId()); e.setCodigo(d.getTipoPrueba().getCodigo());
        e.setDescripcion(d.getTipoPrueba().getDescripcion()); e.setUnidadMedida(d.getTipoPrueba().getUnidadMedida());
        e.setValorObtenido(d.getValorObtenido()); e.setMinimo(d.getRangoPermitido().getMinimo());
        e.setMaximo(d.getRangoPermitido().getMaximo()); e.setResultado(d.getResultado());
        e.setDefectos(d.getDefectos().stream().map(this::toEntity).toList());
        return e;
    }
    private DefectoDetectadoEntity toEntity(DefectoDetectado d) {
        DefectoDetectadoEntity e = new DefectoDetectadoEntity();
        e.setDefectoId(d.getDefectoId()); e.setDescripcion(d.getDescripcion()); e.setSeveridad(d.getSeveridad());
        return e;
    }
}
