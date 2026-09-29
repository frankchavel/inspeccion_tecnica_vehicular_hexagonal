package org.centro.msvc_vehiculo.infrastructure.controllers;

import jakarta.validation.Valid;
import org.centro.msvc_vehiculo.application.usecase.ActualizarVehiculoUseCase;
import org.centro.msvc_vehiculo.application.usecase.CrearVehiculoUseCase;
import org.centro.msvc_vehiculo.application.usecase.EliminarVehiculoUseCase;
import org.centro.msvc_vehiculo.application.usecase.ObtenerVehiculoUseCase;
import org.centro.msvc_vehiculo.domain.model.Vehiculo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controlador REST para el recurso Vehiculo.
 * Inyecta los UseCases por constructor (sin @Autowired).
 * Todos los endpoints bajo /api/vehiculo.
 */
@RestController
@RequestMapping("/api/vehiculo")
public class VehiculoController {

    private final CrearVehiculoUseCase crearUseCase;
    private final ObtenerVehiculoUseCase obtenerUseCase;
    private final ActualizarVehiculoUseCase actualizarUseCase;
    private final EliminarVehiculoUseCase eliminarUseCase;

    public VehiculoController(
            CrearVehiculoUseCase crearUseCase,
            ObtenerVehiculoUseCase obtenerUseCase,
            ActualizarVehiculoUseCase actualizarUseCase,
            EliminarVehiculoUseCase eliminarUseCase) {
        this.crearUseCase = crearUseCase;
        this.obtenerUseCase = obtenerUseCase;
        this.actualizarUseCase = actualizarUseCase;
        this.eliminarUseCase = eliminarUseCase;
    }

    /**
     * GET /api/vehiculo
     * Retorna 200 OK con la lista de todos los vehículos.
     */
    @GetMapping
    public ResponseEntity<List<Vehiculo>> listar() {
        return ResponseEntity.ok(obtenerUseCase.obtenerTodos());
    }

    /**
     * GET /api/vehiculo/{id}
     * Retorna 200 OK si existe, 404 Not Found si no.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Vehiculo> op = obtenerUseCase.obtenerPorId(id);
        if (op.isPresent()) {
            return ResponseEntity.ok(op.get());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * POST /api/vehiculo
     * Retorna 201 Created si válido y placa no duplicada.
     * Retorna 400 Bad Request si hay error de validación o placa duplicada.
     */
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Vehiculo vehiculo, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(buildValidationErrors(result));
        }

        Optional<Vehiculo> existente = obtenerUseCase.obtenerPorPlaca(vehiculo.getPlaca());
        if (existente.isPresent()) {
            return ResponseEntity.badRequest().body("Ya existe un vehículo con esa placa!");
        }

        Vehiculo creado = crearUseCase.crear(vehiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    /**
     * PUT /api/vehiculo/{id}
     * Retorna 201 Created si se actualiza exitosamente.
     * Retorna 404 Not Found si el ID no existe.
     * Retorna 400 Bad Request si hay conflicto de placa o campos inválidos.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(
            @Valid @RequestBody Vehiculo vehiculo,
            BindingResult result,
            @PathVariable Long id) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(buildValidationErrors(result));
        }

        // Verificar conflicto de placa con otro vehículo distinto
        Optional<Vehiculo> porPlaca = obtenerUseCase.obtenerPorPlaca(vehiculo.getPlaca());
        if (porPlaca.isPresent() && !porPlaca.get().getVehiculoId().equals(id)) {
            return ResponseEntity.badRequest().body("Ya existe un vehículo con esa placa!");
        }

        Optional<Vehiculo> actualizado = actualizarUseCase.actualizar(id, vehiculo);
        if (actualizado.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(actualizado.get());
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * DELETE /api/vehiculo/{id}
     * Retorna 204 No Content si se eliminó, 404 Not Found si no existía.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        boolean eliminado = eliminarUseCase.eliminar(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // ---- Utilidades ----

    private Map<String, String> buildValidationErrors(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage())
        );
        return errores;
    }
}
