package br.com.fiap.soat.service.consumer;

import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.dto.StatusPedidoDto;
import br.com.fiap.soat.service.contract.Service;
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
public class NotificarProducaoService implements
    Service<Long, ResponseEntity<ResponseWrapper<StatusPedidoDto>>> {

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
   */
  public ResponseEntity<ResponseWrapper<StatusPedidoDto>> execute(Long numeroPedido) {
    
    String url = "http://localhost:8081/produtos/validar";

    return restTemplate.exchange(
        url,
        HttpMethod.POST,
        new HttpEntity<>(numeroPedido),
        new ParameterizedTypeReference<ResponseWrapper<StatusPedidoDto>>() {});    
  }
}
