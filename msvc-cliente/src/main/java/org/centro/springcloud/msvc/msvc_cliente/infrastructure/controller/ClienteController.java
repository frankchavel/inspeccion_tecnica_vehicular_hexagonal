package org.centro.springcloud.msvc.msvc_cliente.infrastructure.controller;

import java.util.List;
import org.centro.springcloud.msvc.msvc_cliente.domain.model.Cliente;
import org.centro.springcloud.msvc.msvc_cliente.domain.port.in.ActualizarClienteUseCase;
import org.centro.springcloud.msvc.msvc_cliente.domain.port.in.CrearClienteUseCase;
import org.centro.springcloud.msvc.msvc_cliente.domain.port.in.EliminarClienteUseCase;
import org.centro.springcloud.msvc.msvc_cliente.domain.port.in.ObtenerClienteUseCase;
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
@RequestMapping("/api/cliente")
public class ClienteController {
    private final CrearClienteUseCase crearCliente;
    private final ObtenerClienteUseCase obtenerCliente;
    private final ActualizarClienteUseCase actualizarCliente;
    private final EliminarClienteUseCase eliminarCliente;

    public ClienteController(CrearClienteUseCase crearCliente, ObtenerClienteUseCase obtenerCliente,
            ActualizarClienteUseCase actualizarCliente, EliminarClienteUseCase eliminarCliente) {
        this.crearCliente = crearCliente;
        this.obtenerCliente = obtenerCliente;
        this.actualizarCliente = actualizarCliente;
        this.eliminarCliente = eliminarCliente;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        return ResponseEntity.ok(obtenerCliente.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> detalle(@PathVariable Long id) {
        return obtenerCliente.porId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cliente> crear(@RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crearCliente.crear(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> editar(@RequestBody Cliente cliente, @PathVariable Long id) {
        return actualizarCliente.actualizar(id, cliente)
                .map(actualizado -> ResponseEntity.status(HttpStatus.CREATED).body(actualizado))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return eliminarCliente.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
