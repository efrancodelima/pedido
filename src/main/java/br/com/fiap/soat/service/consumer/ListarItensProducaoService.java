package br.com.fiap.soat.service.consumer;

import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.dto.service.response.RegistroProducaoDto;
import br.com.fiap.soat.exception.BadGatewayException;
import br.com.fiap.soat.exception.messages.BadGatewayMessage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Service utilizado para solicitar ao micorsserviço de produção 
 * a lista com os itens em produção ordenados pelo status.
 */
@Component
public class ListarItensProducaoService {

  private final RestTemplate restTemplate;
  private final String baseUrl;
    
  @Autowired
  private ListarItensProducaoService(RestTemplate restTemplate,
      @Value("${pagamento.service.url}") String baseUrl) {
    
    this.restTemplate = restTemplate;
    this.baseUrl = baseUrl;
  }
  
  public List<RegistroProducaoDto> execute() throws BadGatewayException {
    
    String url = baseUrl + "producao/listar/";

    try {
      var response = restTemplate.exchange(
          url,
          HttpMethod.GET,
          new HttpEntity<>(""),
          new ParameterizedTypeReference<ResponseWrapper<List<RegistroProducaoDto>>>() {});

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
