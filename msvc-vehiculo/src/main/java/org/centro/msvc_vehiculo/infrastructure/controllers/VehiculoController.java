package org.centro.msvc_vehiculo.infrastructure.controllers;

import jakarta.validation.Valid;
import java.util.List;
import org.centro.msvc_vehiculo.application.usecase.ActualizarVehiculoUseCase;
import org.centro.msvc_vehiculo.application.usecase.CrearVehiculoUseCase;
import org.centro.msvc_vehiculo.application.usecase.EliminarVehiculoUseCase;
import org.centro.msvc_vehiculo.application.usecase.ObtenerVehiculoUseCase;
import org.centro.msvc_vehiculo.infrastructure.dto.VehiculoRequest;
import org.centro.msvc_vehiculo.infrastructure.dto.VehiculoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private final CrearVehiculoUseCase crearUseCase;
    private final ObtenerVehiculoUseCase obtenerUseCase;
    private final ActualizarVehiculoUseCase actualizarUseCase;
    private final EliminarVehiculoUseCase eliminarUseCase;

    public VehiculoController(CrearVehiculoUseCase crearUseCase,
            ObtenerVehiculoUseCase obtenerUseCase,
            ActualizarVehiculoUseCase actualizarUseCase,
            EliminarVehiculoUseCase eliminarUseCase) {
        this.crearUseCase = crearUseCase;
        this.obtenerUseCase = obtenerUseCase;
        this.actualizarUseCase = actualizarUseCase;
        this.eliminarUseCase = eliminarUseCase;
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody VehiculoRequest request) {
        try {
            VehiculoResponse response = VehiculoResponse.fromDomain(crearUseCase.crear(request.toDomain()));
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<VehiculoResponse>> listar() {
        List<VehiculoResponse> vehiculos = obtenerUseCase.obtenerTodos().stream()
                .map(VehiculoResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculoResponse> detalle(@PathVariable Long id) {
        return obtenerUseCase.obtenerPorId(id)
                .map(VehiculoResponse::fromDomain)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehiculoResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody VehiculoRequest request) {
        return actualizarUseCase.actualizar(id, request.toDomain())
                .map(VehiculoResponse::fromDomain)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return eliminarUseCase.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
