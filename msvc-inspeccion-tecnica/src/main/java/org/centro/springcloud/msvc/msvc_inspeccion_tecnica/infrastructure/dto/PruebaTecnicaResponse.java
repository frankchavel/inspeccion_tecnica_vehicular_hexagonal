package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.infrastructure.dto;
import java.util.List;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model.PruebaTecnica;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model.ResultadoPrueba;
public class PruebaTecnicaResponse {
    private final Long pruebaId; private final String codigo; private final String descripcion;
    private final String unidadMedida; private final Double valorMinimo; private final Double valorMaximo;
    private final Double valorObtenido; private final ResultadoPrueba resultado;
    private final List<DefectoDetectadoResponse> defectos;
    private PruebaTecnicaResponse(PruebaTecnica p) {
        pruebaId=p.getPruebaId(); codigo=p.getTipoPrueba().getCodigo(); descripcion=p.getTipoPrueba().getDescripcion();
        unidadMedida=p.getTipoPrueba().getUnidadMedida(); valorMinimo=p.getRangoPermitido().getMinimo();
        valorMaximo=p.getRangoPermitido().getMaximo(); valorObtenido=p.getValorObtenido(); resultado=p.getResultado();
        defectos=p.getDefectos().stream().map(DefectoDetectadoResponse::fromDomain).toList();
    }
    public static PruebaTecnicaResponse fromDomain(PruebaTecnica p){return new PruebaTecnicaResponse(p);}
    public Long getPruebaId(){return pruebaId;} public String getCodigo(){return codigo;}
    public String getDescripcion(){return descripcion;} public String getUnidadMedida(){return unidadMedida;}
    public Double getValorMinimo(){return valorMinimo;} public Double getValorMaximo(){return valorMaximo;}
    public Double getValorObtenido(){return valorObtenido;} public ResultadoPrueba getResultado(){return resultado;}
    public List<DefectoDetectadoResponse> getDefectos(){return defectos;}
}
