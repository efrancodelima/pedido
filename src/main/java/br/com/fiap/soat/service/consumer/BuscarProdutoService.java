package br.com.fiap.soat.service.consumer;

import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.dto.service.ProdutoDto;
import br.com.fiap.soat.exception.BadGatewayException;
import br.com.fiap.soat.exception.messages.BadGatewayMessage;
import br.com.fiap.soat.service.contract.Service;
import java.util.List;
import java.util.Set;
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
public class BuscarProdutoService implements
    Service<Set<Long>, ResponseEntity<ResponseWrapper<List<ProdutoDto>>>> {

  private final RestTemplate restTemplate;
    
  @Autowired
  private BuscarProdutoService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }
  
  /**
   * Envia uma requisição para o microsserviço producao para que ele verifique
   * se os produtos existem na base de dados.
   *
   * @param codigoProdutos A lista contendo os códigos dos produtos a serem verificados.
   * @return Em caso de sucesso, a lista de objetos key-value onde key é o código do produto
   *     e value informa se o produto existe na base de dados.
   *     Em caso de falha, a mensagem de erro.
   * 
   * @throws BadGatewayException Exceção lançada caso a comunicação com o serviço externo falhe.
   */
  @Override
  public ResponseEntity<ResponseWrapper<List<ProdutoDto>>>
      execute(Set<Long> codigoProdutos) throws BadGatewayException {
    
    String url = "http://localhost:8081/produtos/validar";

    try {
      return restTemplate.exchange(
        url,
        HttpMethod.POST,
        new HttpEntity<>(codigoProdutos),
        new ParameterizedTypeReference<ResponseWrapper<List<ProdutoDto>>>() {});

    } catch (Exception e) {
      throw new BadGatewayException(BadGatewayMessage.PRODUCAO);
    }
    
  }
}
