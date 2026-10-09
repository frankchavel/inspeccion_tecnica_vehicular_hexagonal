package org.centro.springcloud.msvc_ordeninspeccion.infrastructure.adapters;

import feign.FeignException;
import java.util.Optional;
import org.centro.springcloud.msvc_ordeninspeccion.domain.port.out.ClientePort;
import org.centro.springcloud.msvc_ordeninspeccion.infrastructure.clients.ClienteFeignClient;
import org.centro.springcloud.msvc_ordeninspeccion.infrastructure.dto.ClienteResponseRemote;

public class ClienteClientAdapter implements ClientePort {
    private final ClienteFeignClient clienteFeignClient;

    public ClienteClientAdapter(ClienteFeignClient clienteFeignClient) {
        this.clienteFeignClient = clienteFeignClient;
    }

    @Override
    public Optional<Boolean> obtenerHabilitacion(Long clienteId) {
        try {
            ClienteResponseRemote cliente = clienteFeignClient.buscarPorId(clienteId);
            return cliente == null
                    ? Optional.empty()
                    : Optional.of(Boolean.TRUE.equals(cliente.getHabilitado()));
        } catch (FeignException.NotFound exception) {
            return Optional.empty();
        }
    }
}
