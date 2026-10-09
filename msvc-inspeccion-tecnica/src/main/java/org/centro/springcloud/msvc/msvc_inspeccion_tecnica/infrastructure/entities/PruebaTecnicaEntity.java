package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.infrastructure.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model.ResultadoPrueba;

@Entity
@Table(name = "pruebas_tecnicas")
public class PruebaTecnicaEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pruebaId;
    @Column(nullable = false) private String codigo;
    @Column(nullable = false) private String descripcion;
    @Column(name = "unidad_medida", nullable = false) private String unidadMedida;
    @Column(name = "valor_obtenido") private Double valorObtenido;
    @Column(nullable = false) private Double minimo;
    @Column(nullable = false) private Double maximo;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private ResultadoPrueba resultado;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "prueba_id")
    private List<DefectoDetectadoEntity> defectos = new ArrayList<>();
    public PruebaTecnicaEntity() { }
    public Long getPruebaId() { return pruebaId; }
    public void setPruebaId(Long pruebaId) { this.pruebaId = pruebaId; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getUnidadMedida() { return unidadMedida; }
    public void setUnidadMedida(String unidadMedida) { this.unidadMedida = unidadMedida; }
    public Double getValorObtenido() { return valorObtenido; }
    public void setValorObtenido(Double valorObtenido) { this.valorObtenido = valorObtenido; }
    public Double getMinimo() { return minimo; }
    public void setMinimo(Double minimo) { this.minimo = minimo; }
    public Double getMaximo() { return maximo; }
    public void setMaximo(Double maximo) { this.maximo = maximo; }
    public ResultadoPrueba getResultado() { return resultado; }
    public void setResultado(ResultadoPrueba resultado) { this.resultado = resultado; }
    public List<DefectoDetectadoEntity> getDefectos() { return defectos; }
    public void setDefectos(List<DefectoDetectadoEntity> defectos) { this.defectos = defectos; }
}
