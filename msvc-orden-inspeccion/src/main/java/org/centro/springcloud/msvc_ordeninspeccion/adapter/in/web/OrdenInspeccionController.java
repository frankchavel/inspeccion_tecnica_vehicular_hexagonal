package org.centro.springcloud.msvc_ordeninspeccion.adapter.in.web;

import jakarta.validation.Valid;

import org.centro.springcloud.msvc_ordeninspeccion.adapter.in.web.dto.OrdenInspeccionRequest;
import org.centro.springcloud.msvc_ordeninspeccion.adapter.in.web.dto.OrdenInspeccionResponse;
import org.centro.springcloud.msvc_ordeninspeccion.application.service.OrdenInspeccionApplicationService;
import org.centro.springcloud.msvc_ordeninspeccion.domain.models.OrdenInspeccion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orden")
public class OrdenInspeccionController {

    private final OrdenInspeccionApplicationService service;

    public OrdenInspeccionController(
            OrdenInspeccionApplicationService service) {
        this.service = service;
    }

    // Lista todas las órdenes de inspección.
    @GetMapping
    public ResponseEntity<List<OrdenInspeccionResponse>> listar() {

        List<OrdenInspeccionResponse> respuesta =
                service.listar()
                        .stream()
                        .map(OrdenInspeccionResponse::fromDomain)
                        .toList();

        return ResponseEntity.ok(respuesta);
    }

    // Obtiene una orden de inspección por su ID.
    @GetMapping("/{id}")
    public ResponseEntity<OrdenInspeccionResponse> buscarPorId(
            @PathVariable Long id) {

        return service.buscarPorId(id)
                .map(orden ->
                        ResponseEntity.ok(
                                OrdenInspeccionResponse.fromDomain(orden)
                        ))
                .orElse(ResponseEntity.notFound().build());
    }

    // Crea una nueva orden de inspección.
    @PostMapping
    public ResponseEntity<OrdenInspeccionResponse> crear(
            @Valid @RequestBody OrdenInspeccionRequest request) {

        OrdenInspeccion orden = OrdenInspeccion.crear(
                request.getClienteId(),
                request.getVehiculoId(),
                request.getTipo()
        );

        OrdenInspeccion creada = service.crear(orden);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        OrdenInspeccionResponse.fromDomain(creada)
                );
    }

    // Actualiza una orden de inspección existente.
    @PutMapping("/{id}")
    public ResponseEntity<OrdenInspeccionResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody OrdenInspeccionRequest request) {

        OrdenInspeccion actualizada =
                service.actualizar(
                        id,
                        request.getClienteId(),
                        request.getVehiculoId(),
                        request.getTipo()
                );

        return ResponseEntity.ok(
                OrdenInspeccionResponse.fromDomain(actualizada)
        );
    }

    // Elimina una orden de inspección.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }

    // Habilita una orden de inspección.
    @PatchMapping("/{id}/habilitar")
    public ResponseEntity<OrdenInspeccionResponse> habilitar(
            @PathVariable Long id) {

        OrdenInspeccion orden = service.habilitar(id);

        return ResponseEntity.ok(
                OrdenInspeccionResponse.fromDomain(orden)
        );
    }

    // Cancela una orden de inspección.
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<OrdenInspeccionResponse> cancelar(
            @PathVariable Long id) {

        OrdenInspeccion orden = service.cancelar(id);

        return ResponseEntity.ok(
                OrdenInspeccionResponse.fromDomain(orden)
        );
    }
}