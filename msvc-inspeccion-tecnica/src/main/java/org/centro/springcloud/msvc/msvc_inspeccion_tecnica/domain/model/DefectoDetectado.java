package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model;

public class DefectoDetectado {
    private final Long defectoId;
    private final String descripcion;
    private final SeveridadDefecto severidad;

    public DefectoDetectado(Long defectoId, String descripcion, SeveridadDefecto severidad) {
        if (descripcion == null || descripcion.isBlank()) throw new IllegalArgumentException("La descripcion es obligatoria");
        if (severidad == null) throw new IllegalArgumentException("La severidad es obligatoria");
        this.defectoId = defectoId;
        this.descripcion = descripcion;
        this.severidad = severidad;
    }
    public boolean esGrave() { return severidad == SeveridadDefecto.GRAVE; }
    public boolean esMuyGrave() { return severidad == SeveridadDefecto.MUY_GRAVE; }
    public Long getDefectoId() { return defectoId; }
    public String getDescripcion() { return descripcion; }
    public SeveridadDefecto getSeveridad() { return severidad; }
}
