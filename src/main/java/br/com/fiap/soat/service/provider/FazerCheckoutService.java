package br.com.fiap.soat.service.provider;

import br.com.fiap.soat.dto.controller.ItemPedidoDto;
import br.com.fiap.soat.dto.controller.PedidoDto;
import br.com.fiap.soat.dto.service.PagRequestDto;
import br.com.fiap.soat.dto.service.ProdutoDto;
import br.com.fiap.soat.dto.service.StatusPedidoDto;
import br.com.fiap.soat.entity.ClienteJpa;
import br.com.fiap.soat.entity.PedidoJpa;
import br.com.fiap.soat.exception.BadGatewayException;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.exception.messages.BadGatewayMessage;
import br.com.fiap.soat.exception.messages.NotFoundMessage;
import br.com.fiap.soat.mapper.PedidoMapper;
import br.com.fiap.soat.repository.ClienteRepository;
import br.com.fiap.soat.repository.PedidoRepository;
import br.com.fiap.soat.service.consumer.BuscarProdutoService;
import br.com.fiap.soat.service.consumer.NotificarPagamentoService;
import br.com.fiap.soat.service.consumer.NotificarProducaoService;
import br.com.fiap.soat.validator.PedidoValidator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Service para fazer o checkout do pedido.
 */
@Component
public class FazerCheckoutService {

  private final PedidoRepository pedidoRepository;
  private final ClienteRepository clienteRepository;
  private final PedidoValidator validator;
  private final PedidoMapper mapper;
  private final BuscarProdutoService buscarProdutosService;
  private final NotificarProducaoService notificarProducaoService;
  private final NotificarPagamentoService notificarPagamentoService;

  /**
   * O construtor público do service.
   *
   * @param repository O repositório para acesso ao banco de dados.
   */
  @Autowired
  public FazerCheckoutService(PedidoRepository repository, ClienteRepository clienteRepository,
      PedidoValidator validator, PedidoMapper mapper, BuscarProdutoService buscarProdutosService, 
      NotificarProducaoService producaoService, NotificarPagamentoService pagamentoService) {

    this.pedidoRepository = repository;
    this.clienteRepository = clienteRepository;
    this.validator = validator;
    this.mapper = mapper;
    this.buscarProdutosService = buscarProdutosService;
    this.notificarProducaoService = producaoService;
    this.notificarPagamentoService = pagamentoService;
  }

  /**
   * Faz o checkout do pedido.
   *
   * @param pedidoDto O pedido para o checkout.
   * @return O status do pedido.
   * @throws BadRequestException Exceção do tipo bad request lançada pelo método.
   * @throws BadGatewayException Exceção da aplicação lançada pelo método.
   * @throws NotFoundException Exceção do tipo not found lançada pelo método.
   */
  // @Override
  public StatusPedidoDto execute(PedidoDto pedidoDto)
      throws BadGatewayException, BadRequestException, NotFoundException {

    validator.validar(pedidoDto);

    var clienteJpa = buscarCliente(pedidoDto.getCodigoCliente());
    var produtosDto = buscarProdutos(pedidoDto.getItens());

    var pedidoJpa = mapper.toEntity(pedidoDto, produtosDto, clienteJpa);

    pedidoJpa = pedidoRepository.save(pedidoJpa);
    
    notificarSistemaPagamento(pedidoJpa);
    
    return notificarSistemaProducao(pedidoJpa.getNumero());
  }

  private ClienteJpa buscarCliente(Long codigoCliente) throws NotFoundException {
    
    if (codigoCliente == null) {
      return null;
    }

    var optionalClienteJpa = clienteRepository.findById(codigoCliente);
    
    if (optionalClienteJpa.isPresent()) {
      return optionalClienteJpa.get();
    } else {
      throw new NotFoundException(NotFoundMessage.COD_CLIENTE);
    }
  }

  private List<ProdutoDto> buscarProdutos(List<ItemPedidoDto> listaItens)
      throws BadGatewayException, NotFoundException {

    // Consulta o service de integração
    Set<Long> codigoProdutos = new HashSet<>();
    for (ItemPedidoDto item : listaItens) {
      codigoProdutos.add(item.getCodigoProduto());
    }

    var response = buscarProdutosService.execute(codigoProdutos);

    // Verifica a resposta do service
    var responseBody = response.getBody();

    if (response.getStatusCode() != HttpStatus.OK
        || responseBody == null 
        || responseBody.getData().isEmpty()) {
      
      throw new BadGatewayException(BadGatewayMessage.PRODUCAO);
    }

    var listaProdutos = responseBody.getData();
    
    for (var p : listaProdutos) {
      if (p == null) {
        throw new NotFoundException(NotFoundMessage.COD_PRODUTO);
      }
    }

    return listaProdutos;
  }
  
  private void notificarSistemaPagamento(PedidoJpa pedido) throws BadGatewayException {
    
    var requisicao = new PagRequestDto();
    requisicao.setNumeroPedido(pedido.getNumero());
    requisicao.setValorPedido(pedido.getValor());

    var responsePag = notificarPagamentoService.execute(requisicao);
    if (responsePag.getStatusCode() == HttpStatus.NO_CONTENT) {
      throw new BadGatewayException(BadGatewayMessage.PAGAMENTO);
    }
  }

  private StatusPedidoDto notificarSistemaProducao(long numeroPedido) throws BadGatewayException {

    var response = notificarProducaoService.execute(numeroPedido);
    var responseBody = response.getBody();

    if (response.getStatusCode() == HttpStatus.OK && responseBody != null) {
      return responseBody.getData();

    } else {
      throw new BadGatewayException(BadGatewayMessage.PRODUCAO);
    }
  }
}
