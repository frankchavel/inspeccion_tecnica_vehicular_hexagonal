package org.centro.springcloud.msvc.msvc_inspeccion_tecnica.infrastructure.controllers;

import jakarta.validation.Valid;
import java.util.List;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.application.usecase.*;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.domain.model.InspeccionTecnica;
import org.centro.springcloud.msvc.msvc_inspeccion_tecnica.infrastructure.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inspecciones")
public class InspeccionTecnicaController {
    private final CrearInspeccionTecnicaUseCase crear;
    private final ObtenerInspeccionTecnicaUseCase obtener;
    private final ActualizarInspeccionTecnicaUseCase actualizar;
    private final EliminarInspeccionTecnicaUseCase eliminar;
    private final AgregarPruebaUseCase agregarPrueba;
    private final IniciarInspeccionUseCase iniciar;
    private final RegistrarResultadoPruebaUseCase registrarResultado;
    private final RegistrarDefectoUseCase registrarDefecto;
    private final FinalizarInspeccionUseCase finalizar;
    public InspeccionTecnicaController(CrearInspeccionTecnicaUseCase crear, ObtenerInspeccionTecnicaUseCase obtener,
            ActualizarInspeccionTecnicaUseCase actualizar, EliminarInspeccionTecnicaUseCase eliminar,
            AgregarPruebaUseCase agregarPrueba, IniciarInspeccionUseCase iniciar,
            RegistrarResultadoPruebaUseCase registrarResultado, RegistrarDefectoUseCase registrarDefecto,
            FinalizarInspeccionUseCase finalizar) {
        this.crear=crear; this.obtener=obtener; this.actualizar=actualizar; this.eliminar=eliminar;
        this.agregarPrueba=agregarPrueba; this.iniciar=iniciar; this.registrarResultado=registrarResultado;
        this.registrarDefecto=registrarDefecto; this.finalizar=finalizar;
    }
    @PostMapping public ResponseEntity<?> crear(@Valid @RequestBody CrearInspeccionRequest request) {
        try {
            InspeccionTecnica i=InspeccionTecnica.crear(request.getOrdenInspeccionId(), request.getVehiculoId());
            return ResponseEntity.status(HttpStatus.CREATED).body(InspeccionTecnicaResponse.fromDomain(crear.crear(i)));
        } catch (IllegalArgumentException e) { return ResponseEntity.badRequest().body(e.getMessage()); }
    }
    @GetMapping public ResponseEntity<List<InspeccionTecnicaResponse>> listar() {
        return ResponseEntity.ok(obtener.listar().stream().map(InspeccionTecnicaResponse::fromDomain).toList());
    }
    @GetMapping("/{id}") public ResponseEntity<InspeccionTecnicaResponse> obtener(@PathVariable Long id) {
        return obtener.obtenerPorId(id).map(InspeccionTecnicaResponse::fromDomain).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}") public ResponseEntity<?> actualizar(@PathVariable Long id,
            @Valid @RequestBody ActualizarInspeccionRequest request) {
        try {
            return actualizar.actualizar(id, request.getOrdenInspeccionId(), request.getVehiculoId())
                    .map(InspeccionTecnicaResponse::fromDomain).map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) { return ResponseEntity.badRequest().body(e.getMessage()); }
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return eliminar.eliminar(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/pruebas")
    public ResponseEntity<?> agregarPrueba(@PathVariable Long id, @Valid @RequestBody AgregarPruebaRequest request) {
        return ejecutar(() -> agregarPrueba.agregarPrueba(id, request.toDomain()));
    }

    @PutMapping("/{id}/iniciar")
    public ResponseEntity<?> iniciar(@PathVariable Long id) {
        return ejecutar(() -> iniciar.iniciar(id));
    }

    @PutMapping("/{id}/pruebas/{pruebaId}/medicion")
    public ResponseEntity<?> registrarMedicion(@PathVariable Long id, @PathVariable Long pruebaId,
            @Valid @RequestBody RegistrarMedicionRequest request) {
        return ejecutar(() -> registrarResultado.registrarMedicion(id, pruebaId, request.getValorObtenido()));
    }

    @PostMapping("/{id}/pruebas/{pruebaId}/defectos")
    public ResponseEntity<?> registrarDefecto(@PathVariable Long id, @PathVariable Long pruebaId,
            @Valid @RequestBody RegistrarDefectoRequest request) {
        return ejecutar(() -> registrarDefecto.registrarDefecto(id, pruebaId, request.toDomain()));
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<?> finalizar(@PathVariable Long id) {
        return ejecutar(() -> finalizar.finalizar(id));
    }

    private ResponseEntity<?> ejecutar(Operacion operacion) {
        try {
            return operacion.ejecutar().map(InspeccionTecnicaResponse::fromDomain)
                    .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException | IllegalStateException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @FunctionalInterface
    private interface Operacion { java.util.Optional<InspeccionTecnica> ejecutar(); }
}
