package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model;

public class TipoPrueba {
    private final String codigo;
    private final String descripcion;
    private final String unidadMedida;

    public TipoPrueba(String codigo, String descripcion, String unidadMedida) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.unidadMedida = unidadMedida;
        if (!esValido()) throw new IllegalArgumentException("Los datos del tipo de prueba son obligatorios");
    }

    public boolean esValido() {
        return codigo != null && !codigo.isBlank()
                && descripcion != null && !descripcion.isBlank()
                && unidadMedida != null && !unidadMedida.isBlank();
    }
    public String getCodigo() { return codigo; }
    public String getDescripcion() { return descripcion; }
    public String getUnidadMedida() { return unidadMedida; }
}
