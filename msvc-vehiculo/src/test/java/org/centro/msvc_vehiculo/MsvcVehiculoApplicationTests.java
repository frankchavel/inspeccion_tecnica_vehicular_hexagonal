package org.centro.msvc_vehiculo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.centro.msvc_vehiculo.domain.model.Soat;
import org.centro.msvc_vehiculo.domain.model.TituloPropiedad;
import org.centro.msvc_vehiculo.domain.model.Vehiculo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MsvcVehiculoApplicationTests {

    @Value("${local.server.port}")
    private int port;

    private final HttpClient http = HttpClient.newHttpClient();

    @Test
    void crudVehiculoUsaDtosYProtegeIdentidadDocumental() throws Exception {
        String base = "http://localhost:" + port + "/api/vehiculos";
        String fechaSoat = LocalDate.now().plusYears(1).toString();

        HttpResponse<String> creado = send("POST", base,
                vehiculoJson("ABC-123", "Toyota", "Corolla", 2024,
                        "SOAT-001", fechaSoat, "TITULO-001", true));
        assertEquals(201, creado.statusCode());
        assertTrue(creado.body().contains("\"habilitado\":true"));

        Matcher idMatcher = Pattern.compile("\"vehiculoId\":(\\d+)").matcher(creado.body());
        assertTrue(idMatcher.find(), creado.body());
        String id = idMatcher.group(1);

        HttpResponse<String> lista = send("GET", base, null);
        assertEquals(200, lista.statusCode());
        assertTrue(lista.body().contains("\"vehiculoId\":" + id));

        HttpResponse<String> detalle = send("GET", base + "/" + id, null);
        assertEquals(200, detalle.statusCode());
        assertTrue(detalle.body().contains("\"placa\":\"ABC-123\""));

        HttpResponse<String> actualizado = send("PUT", base + "/" + id,
                vehiculoJson("ZZZ-999", "Honda", "Civic", 2025,
                        "SOAT-999", fechaSoat, "TITULO-999", false));
        assertEquals(200, actualizado.statusCode());
        assertTrue(actualizado.body().contains("\"marca\":\"Honda\""));
        assertTrue(actualizado.body().contains("\"modelo\":\"Civic\""));
        assertTrue(actualizado.body().contains("\"anioFabricacion\":2025"));
        assertTrue(actualizado.body().contains("\"placa\":\"ABC-123\""));
        assertTrue(actualizado.body().contains("\"numeroSoat\":\"SOAT-001\""));
        assertTrue(actualizado.body().contains("\"numeroTituloPropiedad\":\"TITULO-001\""));
        assertTrue(actualizado.body().contains("\"tituloValido\":true"));

        HttpResponse<String> eliminado = send("DELETE", base + "/" + id, null);
        assertEquals(204, eliminado.statusCode());
        assertEquals(404, send("GET", base + "/" + id, null).statusCode());
        assertEquals(404, send("DELETE", base + "/" + id, null).statusCode());
    }

    @Test
    void rechazaPlacaDuplicada() throws Exception {
        String base = "http://localhost:" + port + "/api/vehiculos";
        String placa = "DUP-" + System.nanoTime();
        String body = vehiculoJson(placa, "Toyota", "Yaris", 2023,
                "SOAT-DUP", LocalDate.now().plusYears(1).toString(), "TITULO-DUP", true);

        assertEquals(201, send("POST", base, body).statusCode());
        HttpResponse<String> duplicado = send("POST", base, body);

        assertEquals(400, duplicado.statusCode());
        assertTrue(duplicado.body().contains("Ya existe un vehículo registrado con la placa indicada"));
    }

    @Test
    void validaCamposObligatorios() throws Exception {
        String base = "http://localhost:" + port + "/api/vehiculos";
        HttpResponse<String> response = send("POST", base,
                vehiculoJson("", "", "", null, "", null, "", null));
        assertEquals(400, response.statusCode());
    }

    @Test
    void dominioEvaluaSoatTituloYHabilitacion() {
        TituloPropiedad tituloValido = new TituloPropiedad("TITULO-001", true);
        Vehiculo habilitado = new Vehiculo(null, "ABC-123", "Toyota", "Corolla", 2024,
                new Soat("SOAT-001", LocalDate.now()), tituloValido);
        Vehiculo soatVencido = new Vehiculo(null, "DEF-456", "Honda", "Civic", 2023,
                new Soat("SOAT-002", LocalDate.now().minusDays(1)), tituloValido);

        assertTrue(habilitado.tieneSoatVigente());
        assertTrue(habilitado.tieneTituloValido());
        assertTrue(habilitado.estaHabilitado());
        assertTrue(!soatVencido.tieneSoatVigente());
        assertTrue(!soatVencido.estaHabilitado());
    }

    private String vehiculoJson(String placa, String marca, String modelo,
            Integer anioFabricacion, String numeroSoat, String fechaVencimientoSoat,
            String numeroTituloPropiedad, Boolean tituloValido) {
        String anio = anioFabricacion == null ? "null" : anioFabricacion.toString();
        String fecha = fechaVencimientoSoat == null ? "null" : "\"" + fechaVencimientoSoat + "\"";
        String valido = tituloValido == null ? "null" : tituloValido.toString();
        return "{\"placa\":\"" + placa
                + "\",\"marca\":\"" + marca
                + "\",\"modelo\":\"" + modelo
                + "\",\"anioFabricacion\":" + anio
                + ",\"numeroSoat\":\"" + numeroSoat
                + "\",\"fechaVencimientoSoat\":" + fecha
                + ",\"numeroTituloPropiedad\":\"" + numeroTituloPropiedad
                + "\",\"tituloValido\":" + valido + "}";
    }

    private HttpResponse<String> send(String method, String url, String body) throws Exception {
        HttpRequest.Builder request = HttpRequest.newBuilder(URI.create(url));
        if (body == null) {
            request.method(method, HttpRequest.BodyPublishers.noBody());
        } else {
            request.header("Content-Type", "application/json");
            request.method(method, HttpRequest.BodyPublishers.ofString(body));
        }
        return http.send(request.build(), HttpResponse.BodyHandlers.ofString());
    }
}
