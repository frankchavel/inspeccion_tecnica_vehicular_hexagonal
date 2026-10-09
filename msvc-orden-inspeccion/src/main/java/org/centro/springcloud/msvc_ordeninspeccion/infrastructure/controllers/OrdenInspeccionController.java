package org.centro.springcloud.msvc_ordeninspeccion.infrastructure.controllers;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.centro.springcloud.msvc_ordeninspeccion.application.usecase.ActualizarOrdenInspeccionUseCase;
import org.centro.springcloud.msvc_ordeninspeccion.application.usecase.CrearOrdenInspeccionUseCase;
import org.centro.springcloud.msvc_ordeninspeccion.application.usecase.EliminarOrdenInspeccionUseCase;
import org.centro.springcloud.msvc_ordeninspeccion.application.usecase.ObtenerOrdenInspeccionUseCase;
import org.centro.springcloud.msvc_ordeninspeccion.application.usecase.ValidarOrdenInspeccionUseCase;
import org.centro.springcloud.msvc_ordeninspeccion.domain.model.OrdenInspeccion;
import org.centro.springcloud.msvc_ordeninspeccion.infrastructure.dto.OrdenInspeccionRequest;
import org.centro.springcloud.msvc_ordeninspeccion.infrastructure.dto.OrdenInspeccionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ordenes-inspeccion")
public class OrdenInspeccionController {
    private final CrearOrdenInspeccionUseCase crearUseCase;
    private final ObtenerOrdenInspeccionUseCase obtenerUseCase;
    private final ActualizarOrdenInspeccionUseCase actualizarUseCase;
    private final EliminarOrdenInspeccionUseCase eliminarUseCase;
    private final ValidarOrdenInspeccionUseCase validarUseCase;

    public OrdenInspeccionController(CrearOrdenInspeccionUseCase crearUseCase,
                                     ObtenerOrdenInspeccionUseCase obtenerUseCase,
                                     ActualizarOrdenInspeccionUseCase actualizarUseCase,
                                     EliminarOrdenInspeccionUseCase eliminarUseCase,
                                     ValidarOrdenInspeccionUseCase validarUseCase) {
        this.crearUseCase = crearUseCase;
        this.obtenerUseCase = obtenerUseCase;
        this.actualizarUseCase = actualizarUseCase;
        this.eliminarUseCase = eliminarUseCase;
        this.validarUseCase = validarUseCase;
    }

    @PutMapping("/{id}/validar")
    public ResponseEntity<?> validar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(OrdenInspeccionResponse.fromDomain(validarUseCase.validar(id)));
        } catch (NoSuchElementException exception) {
            return respuestaError(HttpStatus.NOT_FOUND, exception.getMessage());
        } catch (IllegalStateException exception) {
            return respuestaError(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody OrdenInspeccionRequest request) {
        try {
            OrdenInspeccion orden = OrdenInspeccion.crear(
                    request.getClienteId(), request.getVehiculoId(), request.getTipoOrden());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(OrdenInspeccionResponse.fromDomain(crearUseCase.crear(orden)));
        } catch (RuntimeException exception) {
            return respuestaError(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<OrdenInspeccionResponse>> listar() {
        return ResponseEntity.ok(obtenerUseCase.listar().stream()
                .map(OrdenInspeccionResponse::fromDomain).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenInspeccionResponse> obtenerPorId(@PathVariable Long id) {
        return obtenerUseCase.obtenerPorId(id)
                .map(OrdenInspeccionResponse::fromDomain)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @Valid @RequestBody OrdenInspeccionRequest request) {
        try {
            OrdenInspeccion orden = actualizarUseCase.actualizar(
                    id, request.getClienteId(), request.getVehiculoId(), request.getTipoOrden());
            return ResponseEntity.ok(OrdenInspeccionResponse.fromDomain(orden));
        } catch (NoSuchElementException exception) {
            return respuestaError(HttpStatus.NOT_FOUND, exception.getMessage());
        } catch (RuntimeException exception) {
            return respuestaError(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            eliminarUseCase.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException exception) {
            return respuestaError(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    private ResponseEntity<Map<String, String>> respuestaError(HttpStatus estado, String mensaje) {
        return ResponseEntity.status(estado).body(Map.of("mensaje", mensaje));
    }
}
