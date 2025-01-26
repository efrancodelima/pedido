package br.com.fiap.soat.service.consumer;

import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.dto.service.NovoPagamentoDto;
import br.com.fiap.soat.dto.service.PagamentoDto;
import br.com.fiap.soat.exception.BadGatewayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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
   * @param requisicao A requisição contendo o número e o valor do pedido.
   * @return Em caso de sucesso, um objeto wrapper com campos nulos.
   *      Em caso de falha, a mensagem de erro.
   * 
   * @throws BadGatewayException Exceção lançada caso a comunicação com o serviço externo falhe.
   */
  public void execute(NovoPagamentoDto requisicao)
      throws BadGatewayException {
    
    String url = "http://localhost:8081/pagamento/novo/";

    try {
      restTemplate.exchange(
        url,
        HttpMethod.POST,
        new HttpEntity<>(requisicao),
        new ParameterizedTypeReference<ResponseWrapper<PagamentoDto>>() {});

    } catch (Exception e) {
      throw new BadGatewayException(e.getMessage());
    }
  }
}
