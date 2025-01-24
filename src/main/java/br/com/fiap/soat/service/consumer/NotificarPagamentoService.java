package br.com.fiap.soat.service.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.fiap.soat.controller.wrapper.ResponseWrapper;

/**
 * Service utilizado para se comunicar com o microsserviço de produção.
 */
@Component
public class NotificarPagamentoService {

  private final RestTemplate restTemplate;
    
  @Autowired
  private NotificarPagamentoService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }
  
  /**
   * Envia uma requisição para o microsserviço de pagamento para que ele gere
   * um novo pagamento para o pedido.
   *
   * @param numeroPedido O número do pedido.
   * @return Em caso de sucesso, um objeto wrapper com campos nulos.
   *      Em caso de falha, a mensagem de erro.
   */
  public ResponseEntity<ResponseWrapper<Void>> execute(Long numeroPedido) {
    
    String url = "http://localhost:8082/novo";

    return restTemplate.exchange(
        url,
        HttpMethod.POST,
        new HttpEntity<>("codigoProdutos"),
        new ParameterizedTypeReference<ResponseWrapper<Void>>() {});    
  }
}
