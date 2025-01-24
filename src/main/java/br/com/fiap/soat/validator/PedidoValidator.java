package br.com.fiap.soat.validator;

import br.com.fiap.soat.dto.ItemPedidoDto;
import br.com.fiap.soat.dto.PedidoDto;
import br.com.fiap.soat.dto.ProdutoExistsDto;
import br.com.fiap.soat.exception.BadGatewayException;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.exception.messages.BadGatewayMessage;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import br.com.fiap.soat.exception.messages.NotFoundMessage;
import br.com.fiap.soat.repository.ClienteRepository;
import br.com.fiap.soat.service.consumer.VerificarProdutosExistemService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Responsável por validar o pedido recebido na requisição ao microsserviço.
 */
@Component
public class PedidoValidator {

  private final ClienteRepository clienteRepository;
  private final VerificarProdutosExistemService produtosExistemService;

  @Autowired
  private PedidoValidator(ClienteRepository clienteRepository,
      VerificarProdutosExistemService produtosService) {

    this.clienteRepository = clienteRepository;
    this.produtosExistemService = produtosService;
  }

  /**
   * valida um objeto do tipo PedidoDto.
   *
   * @param pedidoDto O objeto a ser validado.
   * @throws BadRequestException Exceção do tipo bad request lançada durante a validação.
   * @throws BadGatewayException Exceção da aplicação lançada durante a validação.
   * @throws NotFoundException Exceção do tipo not found lançada durante a validação.
   */
  public void validar(PedidoDto pedidoDto) throws BadGatewayException,
      BadRequestException, NotFoundException {

    validarCodigoCliente(pedidoDto.getCodigoCliente());
    validarItensPedido(pedidoDto.getItens());
  }

  private void validarCodigoCliente(Long codigoCliente)
      throws BadRequestException, NotFoundException {

    if (codigoCliente != null) {

      if (codigoCliente < 1) {
        throw new BadRequestException(BadRequestMessage.CODIGO_CLIENTE);
      }
  
      var clienteExiste = clienteRepository.existsById(codigoCliente);
      if (!clienteExiste) {
        throw new NotFoundException(NotFoundMessage.COD_CLIENTE);
      }
    }
  }

  private void validarItensPedido(List<ItemPedidoDto> listaItens)
      throws BadGatewayException, BadRequestException, NotFoundException {

    if (listaItens == null || listaItens.isEmpty()) {
      throw new BadRequestException(BadRequestMessage.ITEM_MIN);
    }

    for (ItemPedidoDto item : listaItens) {
      if (item.getQuantidade() == null || item.getQuantidade() < 1) {
        throw new BadRequestException(BadRequestMessage.QTDE_ITEM);
      }

      if (item.getCodigoProduto() == null || item.getCodigoProduto() < 1) {
        throw new BadRequestException(BadRequestMessage.CODIGO_PRODUTO);
      }
    }

    var itensExists = verificarSeProdutosExistem(listaItens);

    for (var item : itensExists) {
      if (!item.isExists()) {
        throw new NotFoundException(NotFoundMessage.COD_PRODUTO);
      }
    }
  }

  private List<ProdutoExistsDto> verificarSeProdutosExistem(List<ItemPedidoDto> listaItens)
      throws BadGatewayException {

    // Consulta o service de integração
    Set<Long> codigoProdutos = new HashSet<>();
    for (ItemPedidoDto item : listaItens) {
      codigoProdutos.add(item.getCodigoProduto());
    }

    var response = produtosExistemService.execute(codigoProdutos);

    // Verifica a resposta do service
    var responseBody = response.getBody();

    if (response.getStatusCode() == HttpStatus.OK
        && responseBody != null && !responseBody.getData().isEmpty()) {

      return responseBody.getData();

    } else {
      throw new BadGatewayException(BadGatewayMessage.PRODUCAO);
    }
  }
}
