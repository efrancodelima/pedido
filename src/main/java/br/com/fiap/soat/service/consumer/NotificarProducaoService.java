package br.com.fiap.soat.service.consumer;

import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.dto.service.StatusPedidoDto;
import br.com.fiap.soat.exception.BadGatewayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Service utilizado para se comunicar com o microsserviço de produção.
 */
@Component
public class NotificarProducaoService {

  private final RestTemplate restTemplate;
    
  @Autowired
  private NotificarProducaoService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }
  
  /**
   * Envia uma notificação para o microsserviço producao avisando que um novo pedido foi feito.
   *
   * @param numeroPedido O número do pedido.
   * @return Em caso de sucesso, o status do pedido. Em caso de falha, a mensagem de erro.
   * @throws BadGatewayException Exceção lançada caso a comunicação com o serviço externo falhe.
   */
  public ResponseEntity<ResponseWrapper<StatusPedidoDto>> execute(Long numeroPedido)
      throws BadGatewayException {
    
    String url = "http://localhost:8082/pedido/checkout/" + numeroPedido;

    try {
      return restTemplate.exchange(
        url,
        HttpMethod.POST,
        new HttpEntity<>(""),
        new ParameterizedTypeReference<ResponseWrapper<StatusPedidoDto>>() {});

    } catch (Exception e) {
      throw new BadGatewayException(e.getMessage());
    }
  }
}
