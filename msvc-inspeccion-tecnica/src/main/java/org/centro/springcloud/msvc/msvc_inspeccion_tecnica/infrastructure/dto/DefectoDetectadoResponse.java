package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.infrastructure.dto;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model.DefectoDetectado;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model.SeveridadDefecto;
public class DefectoDetectadoResponse {
    private final Long defectoId; private final String descripcion; private final SeveridadDefecto severidad;
    private DefectoDetectadoResponse(DefectoDetectado d) {
        defectoId=d.getDefectoId(); descripcion=d.getDescripcion(); severidad=d.getSeveridad();
    }
    public static DefectoDetectadoResponse fromDomain(DefectoDetectado d) { return new DefectoDetectadoResponse(d); }
    public Long getDefectoId(){return defectoId;} public String getDescripcion(){return descripcion;}
    public SeveridadDefecto getSeveridad(){return severidad;}
}
