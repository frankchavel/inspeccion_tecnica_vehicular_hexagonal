package org.centro.springcloud.msvc.msvc_cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.centro.springcloud.msvc.msvc_cliente.domain.model.Cliente;
import org.centro.springcloud.msvc.msvc_cliente.domain.model.LicenciaConducir;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MsvcClienteApplicationTests {
    @Value("${local.server.port}")
    private int port;

    private final HttpClient http = HttpClient.newHttpClient();

    @Test
    void clienteCrudUsaDtosYRespetaElAgregado() throws Exception {
        String base = "http://localhost:" + port + "/api/clientes";
        String fechaVigente = LocalDate.now().plusYears(1).toString();

        HttpResponse<String> creado = send("POST", base,
                clienteJson("12345678", "Ana", "Perez", "LIC-001", fechaVigente));
        assertEquals(201, creado.statusCode());
        assertTrue(creado.body().contains("\"documentoIdentidad\":\"12345678\""));
        assertTrue(creado.body().contains("\"numeroLicencia\":\"LIC-001\""));
        assertTrue(creado.body().contains("\"habilitado\":true"));
        Matcher idMatcher = Pattern.compile("\"clienteId\":(\\d+)").matcher(creado.body());
        assertTrue(idMatcher.find(), creado.body());
        String id = idMatcher.group(1);

        HttpResponse<String> lista = send("GET", base, null);
        assertEquals(200, lista.statusCode());
        assertTrue(lista.body().contains("\"clienteId\":" + id));

        HttpResponse<String> detalle = send("GET", base + "/" + id, null);
        assertEquals(200, detalle.statusCode());
        assertTrue(detalle.body().contains("\"fechaVencimientoLicencia\":\"" + fechaVigente + "\""));

        HttpResponse<String> actualizado = send("PUT", base + "/" + id,
                clienteJson("99999999", "Bea", "Gomez", "LIC-999", fechaVigente));
        assertEquals(200, actualizado.statusCode());
        assertTrue(actualizado.body().contains("\"clienteId\":" + id));
        assertTrue(actualizado.body().contains("\"documentoIdentidad\":\"12345678\""));
        assertTrue(actualizado.body().contains("\"numeroLicencia\":\"LIC-001\""));
        assertTrue(actualizado.body().contains("\"nombres\":\"Bea\""));
        assertTrue(actualizado.body().contains("\"apellidos\":\"Gomez\""));

        HttpResponse<String> eliminado = send("DELETE", base + "/" + id, null);
        assertEquals(204, eliminado.statusCode());
        assertEquals(404, send("GET", base + "/" + id, null).statusCode());
        assertEquals(404, send("PUT", base + "/" + id,
                clienteJson("12345678", "Ana", "Perez", "LIC-001", fechaVigente)).statusCode());
        assertEquals(404, send("DELETE", base + "/" + id, null).statusCode());
    }

    @Test
    void rechazaDatosObligatoriosVacios() throws Exception {
        String base = "http://localhost:" + port + "/api/clientes";
        HttpResponse<String> response = send("POST", base,
                clienteJson("", "", "", "", null));
        assertEquals(400, response.statusCode());
    }

    @Test
    void dominioEvaluaVigenciaDeLicencia() {
        Cliente vigente = new Cliente(null, "12345678", "Ana", "Perez",
                new LicenciaConducir("LIC-001", LocalDate.now()));
        Cliente vencido = new Cliente(null, "87654321", "Bea", "Gomez",
                new LicenciaConducir("LIC-002", LocalDate.now().minusDays(1)));

        assertTrue(vigente.validarLicencia());
        assertTrue(vigente.estaHabilitado());
        assertTrue(!vencido.estaHabilitado());
    }

    private String clienteJson(String documento, String nombres, String apellidos,
            String licencia, String fechaVencimiento) {
        String fecha = fechaVencimiento == null ? "null" : "\"" + fechaVencimiento + "\"";
        return "{\"documentoIdentidad\":\"" + documento
                + "\",\"nombres\":\"" + nombres
                + "\",\"apellidos\":\"" + apellidos
                + "\",\"numeroLicencia\":\"" + licencia
                + "\",\"fechaVencimientoLicencia\":" + fecha + "}";
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
