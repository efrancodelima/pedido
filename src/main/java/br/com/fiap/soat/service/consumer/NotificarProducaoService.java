package br.com.fiap.soat.service.consumer;

import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.dto.service.RegistroProducaoDto;
import br.com.fiap.soat.exception.BadGatewayException;
import br.com.fiap.soat.exception.messages.BadGatewayMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Service utilizado para notificar o checkout ao microsserviço de produção.
 */
@Component
public class NotificarProducaoService {

  private final RestTemplate restTemplate;
    
  @Autowired
  private NotificarProducaoService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }
  
  public RegistroProducaoDto execute(Long numeroPedido)
      throws BadGatewayException {
    
    String url = "http://localhost:8082/pedido/receber/" + numeroPedido;

    try {
      var response = restTemplate.exchange(
          url,
          HttpMethod.POST,
          new HttpEntity<>(""),
          new ParameterizedTypeReference<ResponseWrapper<RegistroProducaoDto>>() {});

      var responseBody = response.getBody();

      if (responseBody == null || responseBody.getData() == null) {
        throw new BadGatewayException(BadGatewayMessage.PRODUCAO);
      } else {
        return responseBody.getData();
      }

    } catch (Exception e) {
      throw new BadGatewayException(e.getMessage());
    }
  }
}
