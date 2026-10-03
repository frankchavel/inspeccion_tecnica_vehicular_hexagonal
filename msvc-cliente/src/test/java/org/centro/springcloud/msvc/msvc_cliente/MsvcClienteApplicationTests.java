package org.centro.springcloud.msvc.msvc_cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MsvcClienteApplicationTests {
    @Value("${local.server.port}")
    private int port;

    private final HttpClient http = HttpClient.newHttpClient();

    @Test
    void clienteCrudUsaNumeroLicencia() throws Exception {
        String base = "http://localhost:" + port + "/api/cliente";

        HttpResponse<String> creado = send("POST", base,
                "{\"numeroLicencia\":\"12345678\",\"nombres\":\"Ana\",\"apellidos\":\"Perez\"}");
        assertEquals(201, creado.statusCode());
        assertTrue(creado.body().contains("\"numeroLicencia\":\"12345678\""));
        Matcher idMatcher = Pattern.compile("\"clienteId\":(\\d+)").matcher(creado.body());
        assertTrue(idMatcher.find(), creado.body());
        String id = idMatcher.group(1);

        HttpResponse<String> lista = send("GET", base, null);
        assertEquals(200, lista.statusCode());
        assertTrue(lista.body().contains("\"clienteId\":" + id));

        HttpResponse<String> detalle = send("GET", base + "/" + id, null);
        assertEquals(200, detalle.statusCode());
        assertTrue(detalle.body().contains("\"numeroLicencia\":\"12345678\""));
        assertFalse(detalle.body().contains("numeroDni"));

        HttpResponse<String> actualizado = send("PUT", base + "/" + id,
                "{\"clienteId\":999,\"numeroLicencia\":\"87654321\",\"nombres\":\"Bea\",\"apellidos\":\"Gomez\"}");
        assertEquals(201, actualizado.statusCode());
        assertTrue(actualizado.body().contains("\"clienteId\":" + id));
        assertTrue(actualizado.body().contains("\"numeroLicencia\":\"87654321\""));
        assertTrue(actualizado.body().contains("\"nombres\":\"Bea\""));
        assertTrue(actualizado.body().contains("\"apellidos\":\"Gomez\""));
        assertFalse(actualizado.body().contains("\"clienteId\":999"));

        HttpResponse<String> eliminado = send("DELETE", base + "/" + id, null);
        assertEquals(204, eliminado.statusCode());
        assertEquals(404, send("GET", base + "/" + id, null).statusCode());
        assertEquals(404, send("PUT", base + "/" + id, "{}").statusCode());
        assertEquals(404, send("DELETE", base + "/" + id, null).statusCode());
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
