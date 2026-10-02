package org.centro.springcloud.msvc_ordeninspeccion.adapter.out.client.cliente;

import feign.FeignException;
import org.centro.springcloud.msvc_ordeninspeccion.domain.port.out.ClienteClientPort;
import org.springframework.stereotype.Component;

@Component
public class ClienteClientAdapter implements ClienteClientPort {

    private final ClienteFeignClient clienteFeignClient;

    public ClienteClientAdapter(
            ClienteFeignClient clienteFeignClient) {

        this.clienteFeignClient = clienteFeignClient;
    }

    @Override
    public boolean existeCliente(Long clienteId) {

        try {
            ClienteResponse cliente =
                    clienteFeignClient.buscarPorId(clienteId);

            return cliente != null;

        } catch (FeignException.NotFound e) {

            return false;
        }
    }
}