package pe.tecsup.mantenimiento.equipo.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ClienteClient {

    private final RestClient api;

    public ClienteClient(@Value("${servicios.cliente.url}") String baseUrl) {
        SimpleClientHttpRequestFactory peticiones = new SimpleClientHttpRequestFactory();
        peticiones.setConnectTimeout(2000);
        peticiones.setReadTimeout(3000);
        this.api = RestClient.builder().baseUrl(baseUrl).requestFactory(peticiones).build();
    }

    public void verificarExiste(Long id) {
        try {
            api.get().uri("/clientes/{id}", id).retrieve().toBodilessEntity();
        } catch (HttpClientErrorException.NotFound exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El cliente con ID " + id + " no existe.");
        } catch (RestClientException exception) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
                "No se pudo consultar el servicio de clientes. Intenta nuevamente.");
        }
    }
}
