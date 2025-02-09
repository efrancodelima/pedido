package br.com.fiap.soat.service.consumer;

import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.dto.service.request.CriarPagamentoDto;
import br.com.fiap.soat.dto.service.response.PagamentoDto;
import br.com.fiap.soat.exception.BadGatewayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Service utilizado para notificar o checkout ao microsserviço de produção.
 */
@Component
public class NotificarPagamentoService {

  private final RestTemplate restTemplate;
  private final String baseUrl;
    
  @Autowired
  private NotificarPagamentoService(RestTemplate restTemplate,
      @Value("${pagamento.service.url}") String baseUrl) {
    
    this.restTemplate = restTemplate;
    this.baseUrl = baseUrl;
  }
  
  public void execute(CriarPagamentoDto requisicao) throws BadGatewayException {
    
    String url = baseUrl + "pagamento/criar/";

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
