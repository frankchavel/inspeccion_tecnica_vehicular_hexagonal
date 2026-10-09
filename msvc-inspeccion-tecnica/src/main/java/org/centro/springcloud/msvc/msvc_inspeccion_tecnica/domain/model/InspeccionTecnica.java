package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InspeccionTecnica {
    private final Long inspeccionId;
    private Long ordenInspeccionId;
    private Long vehiculoId;
    private EstadoInspeccion estado;
    private DictamenInspeccion dictamen;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private final List<PruebaTecnica> pruebas;

    public InspeccionTecnica(Long inspeccionId, Long ordenInspeccionId, Long vehiculoId,
                             EstadoInspeccion estado, DictamenInspeccion dictamen,
                             LocalDateTime fechaInicio, LocalDateTime fechaFin,
                             List<PruebaTecnica> pruebas) {
        validarDatos(ordenInspeccionId, vehiculoId);
        this.inspeccionId = inspeccionId;
        this.ordenInspeccionId = ordenInspeccionId;
        this.vehiculoId = vehiculoId;
        this.estado = estado == null ? EstadoInspeccion.PENDIENTE : estado;
        this.dictamen = dictamen == null ? DictamenInspeccion.NO_CONCLUIDO : dictamen;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.pruebas = pruebas == null ? new ArrayList<>() : new ArrayList<>(pruebas);
    }

    public static InspeccionTecnica crear(Long ordenInspeccionId, Long vehiculoId) {
        return new InspeccionTecnica(null, ordenInspeccionId, vehiculoId,
                EstadoInspeccion.PENDIENTE, DictamenInspeccion.NO_CONCLUIDO, null, null, List.of());
    }
    public void actualizarDatos(Long ordenInspeccionId, Long vehiculoId) {
        if (estaFinalizada()) throw new IllegalStateException("No se puede modificar una inspeccion finalizada");
        validarDatos(ordenInspeccionId, vehiculoId);
        this.ordenInspeccionId = ordenInspeccionId;
        this.vehiculoId = vehiculoId;
    }
    public void iniciar() {
        if (estado != EstadoInspeccion.PENDIENTE) {
            throw new IllegalStateException("Solo se puede iniciar una inspeccion pendiente");
        }
        estado = EstadoInspeccion.EN_PROCESO;
        fechaInicio = LocalDateTime.now();
    }
    public void agregarPrueba(PruebaTecnica prueba) {
        if (estado != EstadoInspeccion.PENDIENTE) {
            throw new IllegalStateException("Solo se pueden agregar pruebas a una inspeccion pendiente");
        }
        if (prueba == null) throw new IllegalArgumentException("La prueba es obligatoria");
        pruebas.add(prueba);
    }
    public void registrarMedicion(Long pruebaId, Double valorObtenido) {
        exigirEnProceso();
        buscarPrueba(pruebaId).registrarMedicion(valorObtenido);
    }
    public void registrarDefecto(Long pruebaId, DefectoDetectado defecto) {
        exigirEnProceso();
        buscarPrueba(pruebaId).registrarDefecto(defecto);
    }
    public void finalizar() {
        exigirEnProceso();
        if (pruebas.isEmpty()) throw new IllegalStateException("No se puede finalizar una inspeccion sin pruebas");
        if (pruebas.stream().anyMatch(p -> p.getResultado() == null || p.getResultado() == ResultadoPrueba.PENDIENTE)) {
            throw new IllegalStateException("No se puede finalizar con pruebas pendientes");
        }
        estado = EstadoInspeccion.FINALIZADA;
        fechaFin = LocalDateTime.now();
        calcularDictamen();
    }
    private void calcularDictamen() {
        boolean desaprobada = pruebas.stream().anyMatch(p -> p.getResultado() == ResultadoPrueba.DESAPROBADO);
        boolean todasAprobadas = pruebas.stream().allMatch(p -> p.getResultado() == ResultadoPrueba.APROBADO);
        if (desaprobada) dictamen = DictamenInspeccion.DESAPROBADO;
        else if (todasAprobadas) dictamen = DictamenInspeccion.APROBADO;
        else dictamen = DictamenInspeccion.NO_CONCLUIDO;
    }
    public boolean estaFinalizada() { return estado == EstadoInspeccion.FINALIZADA; }
    private void exigirEnProceso() {
        if (estado != EstadoInspeccion.EN_PROCESO) {
            throw new IllegalStateException("La inspeccion debe estar en proceso");
        }
    }
    private PruebaTecnica buscarPrueba(Long id) {
        return pruebas.stream().filter(p -> id != null && id.equals(p.getPruebaId())).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No existe la prueba indicada"));
    }
    private static void validarDatos(Long ordenId, Long vehiculoId) {
        if (ordenId == null) throw new IllegalArgumentException("La orden de inspeccion es obligatoria");
        if (vehiculoId == null) throw new IllegalArgumentException("El vehiculo es obligatorio");
    }
    public Long getInspeccionId() { return inspeccionId; }
    public Long getOrdenInspeccionId() { return ordenInspeccionId; }
    public Long getVehiculoId() { return vehiculoId; }
    public EstadoInspeccion getEstado() { return estado; }
    public DictamenInspeccion getDictamen() { return dictamen; }
    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public LocalDateTime getFechaFin() { return fechaFin; }
    public List<PruebaTecnica> getPruebas() { return List.copyOf(pruebas); }
}
