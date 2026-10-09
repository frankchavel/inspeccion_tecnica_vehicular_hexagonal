package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.infrastructure.entities;

import jakarta.persistence.*;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model.SeveridadDefecto;

@Entity
@Table(name = "defectos_detectados")
public class DefectoDetectadoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long defectoId;
    @Column(nullable = false) private String descripcion;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private SeveridadDefecto severidad;
    public DefectoDetectadoEntity() { }
    public Long getDefectoId() { return defectoId; }
    public void setDefectoId(Long defectoId) { this.defectoId = defectoId; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public SeveridadDefecto getSeveridad() { return severidad; }
    public void setSeveridad(SeveridadDefecto severidad) { this.severidad = severidad; }
}
