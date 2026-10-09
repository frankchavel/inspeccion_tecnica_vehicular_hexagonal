package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.infrastructure.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model.DictamenInspeccion;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model.EstadoInspeccion;

@Entity
@Table(name = "inspecciones_tecnicas")
public class InspeccionTecnicaEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inspeccionId;
    @Column(name = "orden_inspeccion_id", nullable = false) private Long ordenInspeccionId;
    @Column(name = "vehiculo_id", nullable = false) private Long vehiculoId;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private EstadoInspeccion estado;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private DictamenInspeccion dictamen;
    @Column(name = "fecha_inicio") private LocalDateTime fechaInicio;
    @Column(name = "fecha_fin") private LocalDateTime fechaFin;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "inspeccion_id")
    private List<PruebaTecnicaEntity> pruebas = new ArrayList<>();
    public InspeccionTecnicaEntity() { }
    public Long getInspeccionId() { return inspeccionId; }
    public void setInspeccionId(Long inspeccionId) { this.inspeccionId = inspeccionId; }
    public Long getOrdenInspeccionId() { return ordenInspeccionId; }
    public void setOrdenInspeccionId(Long ordenInspeccionId) { this.ordenInspeccionId = ordenInspeccionId; }
    public Long getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(Long vehiculoId) { this.vehiculoId = vehiculoId; }
    public EstadoInspeccion getEstado() { return estado; }
    public void setEstado(EstadoInspeccion estado) { this.estado = estado; }
    public DictamenInspeccion getDictamen() { return dictamen; }
    public void setDictamen(DictamenInspeccion dictamen) { this.dictamen = dictamen; }
    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDateTime fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDateTime getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDateTime fechaFin) { this.fechaFin = fechaFin; }
    public List<PruebaTecnicaEntity> getPruebas() { return pruebas; }
    public void setPruebas(List<PruebaTecnicaEntity> pruebas) { this.pruebas = pruebas; }
}
