package br.com.fiap.soat.mapper;

import br.com.fiap.soat.dto.controller.ItemPedidoDto;
import br.com.fiap.soat.dto.controller.PedidoDto;
import br.com.fiap.soat.dto.service.ProdutoDto;
import br.com.fiap.soat.entity.ClienteJpa;
import br.com.fiap.soat.entity.ItemPedidoJpa;
import br.com.fiap.soat.entity.PedidoJpa;
import br.com.fiap.soat.exception.BadGatewayException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.exception.messages.BadGatewayMessage;
import br.com.fiap.soat.exception.messages.NotFoundMessage;
import br.com.fiap.soat.repository.ClienteRepository;
import br.com.fiap.soat.service.consumer.BuscarProdutoService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Responsável por mapear um pedido DTO para uma entidade JPA.
 */
@Component
public class PedidoMapper {

  private final ClienteRepository clienteRepository;
  private final BuscarProdutoService buscarProdutosService;

  @Autowired
  private PedidoMapper(ClienteRepository clienteRepository,
      BuscarProdutoService buscarProdutosService) {

    this.clienteRepository = clienteRepository;
    this.buscarProdutosService = buscarProdutosService;
  }

  /**
   * Mapeia um pedido DTO para uma entidade JPA.
   *
   * @param pedidoDto O objeto DTO a ser mapeado.
   * @return A entidade JPA.
   * @throws NotFoundException Exceção do tipo not found lançada pelo método.
   * @throws BadGatewayException Exceção do tipo bad gateway lançada pelo método.
   */
  public PedidoJpa toEntity(PedidoDto pedidoDto)
         throws BadGatewayException, NotFoundException {

    var pedidoJpa = new PedidoJpa();

    var cliente = buscarCliente(pedidoDto.getCodigoCliente());
    pedidoJpa.setClienteJpa(cliente);
    
    var listaItensJpa = getListaItensJpa(pedidoDto);
    pedidoJpa.setItensJpa(listaItensJpa);

    pedidoJpa.setTimestampCheckout(LocalDateTime.now());

    var produtos = buscarProdutos(pedidoDto.getItens());
    var valorPedido = calcularValorPedido(listaItensJpa, produtos);
    pedidoJpa.setValor(valorPedido);

    return pedidoJpa;
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

  private List<ItemPedidoJpa> getListaItensJpa(PedidoDto pedido) {

    var listaItensJpa = new ArrayList<ItemPedidoJpa>();
    for (var itemDto : pedido.getItens()) {
      var itemJpa = ItemPedidoMapper.toEntity(itemDto);
      listaItensJpa.add(itemJpa);
    }
    return listaItensJpa;
  }

  private BigDecimal calcularValorPedido(List<ItemPedidoJpa> itensPedido,
      List<ProdutoDto> produtos) {

    var valorPedido = BigDecimal.ZERO;

    for (var item : itensPedido) {
      
      var produto = produtos.stream()
          .filter(p -> p.getCodigo().equals(item.getCodigoProduto())).findFirst();
      
      if (produto.isPresent()) {

        var valorProduto = produto.get().getPreco();
        var valorItem = valorProduto.multiply(BigDecimal.valueOf(item.getQuantidade()));
        valorPedido = valorPedido.add(valorItem);
      }
    }

    return valorPedido;
  }
    
}
