package org.centro.springcloud.msvc.msvc_cliente.infrastructure.controllers;

import jakarta.validation.Valid;
import java.util.List;
import org.centro.springcloud.msvc.msvc_cliente.application.usecase.ActualizarClienteUseCase;
import org.centro.springcloud.msvc.msvc_cliente.application.usecase.CrearClienteUseCase;
import org.centro.springcloud.msvc.msvc_cliente.application.usecase.EliminarClienteUseCase;
import org.centro.springcloud.msvc.msvc_cliente.application.usecase.ObtenerClienteUseCase;
import org.centro.springcloud.msvc.msvc_cliente.infrastructure.dto.ClienteRequest;
import org.centro.springcloud.msvc.msvc_cliente.infrastructure.dto.ClienteResponse;
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
@RequestMapping("/api/clientes")
public class ClienteController {

    private final CrearClienteUseCase crearCliente;
    private final ObtenerClienteUseCase obtenerCliente;
    private final ActualizarClienteUseCase actualizarCliente;
    private final EliminarClienteUseCase eliminarCliente;

    public ClienteController(CrearClienteUseCase crearCliente,
            ObtenerClienteUseCase obtenerCliente,
            ActualizarClienteUseCase actualizarCliente,
            EliminarClienteUseCase eliminarCliente) {
        this.crearCliente = crearCliente;
        this.obtenerCliente = obtenerCliente;
        this.actualizarCliente = actualizarCliente;
        this.eliminarCliente = eliminarCliente;
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> crear(@Valid @RequestBody ClienteRequest request) {
        ClienteResponse response = ClienteResponse.fromDomain(crearCliente.crear(request.toDomain()));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listar() {
        List<ClienteResponse> clientes = obtenerCliente.listar().stream()
                .map(ClienteResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> detalle(@PathVariable Long id) {
        return obtenerCliente.porId(id)
                .map(ClienteResponse::fromDomain)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ClienteRequest request) {
        return actualizarCliente.actualizar(id, request.toDomain())
                .map(ClienteResponse::fromDomain)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return eliminarCliente.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
