package br.com.fiap.techchallenge.interfacelayer.controllers;

import br.com.fiap.techchallenge.applicationlayer.exceptions.ApplicationException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.ResourceNotFoundException;
import br.com.fiap.techchallenge.applicationlayer.usecases.cliente.BuscarClientePeloCpf;
import br.com.fiap.techchallenge.applicationlayer.usecases.pedido.AtualizarStatusPagamento;
import br.com.fiap.techchallenge.applicationlayer.usecases.pedido.AtualizarStatusPedido;
import br.com.fiap.techchallenge.applicationlayer.usecases.pedido.BuscarPedido;
import br.com.fiap.techchallenge.applicationlayer.usecases.pedido.FazerCheckoutPedido;
import br.com.fiap.techchallenge.applicationlayer.usecases.pedido.ListarPedidos;
import br.com.fiap.techchallenge.applicationlayer.usecases.produto.BuscarProduto;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cpf;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.ItemPedido;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.controllers.adapters.request.ItemPedidoRequestAdapter;
import br.com.fiap.techchallenge.interfacelayer.controllers.adapters.request.PagamentoRequestAdapter;
import br.com.fiap.techchallenge.interfacelayer.controllers.adapters.response.PedidoResponseAdapter;
import br.com.fiap.techchallenge.interfacelayer.controllers.adapters.response.StatusPagamentoResponseAdapter;
import br.com.fiap.techchallenge.interfacelayer.controllers.adapters.response.StatusPedidoResponseAdapter;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.mercadopago.PagamentoDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.ItemPedidoDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.PedidoDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.StatusPagamentoDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.StatusPedidoDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.interfaces.PedidoController;
import br.com.fiap.techchallenge.interfacelayer.gateways.ClienteGateway;
import br.com.fiap.techchallenge.interfacelayer.gateways.PedidoGateway;
import br.com.fiap.techchallenge.interfacelayer.gateways.ProdutoGateway;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Classe PedidoControllerImpl.
 */
@Component
public class PedidoControllerImpl implements PedidoController {

  // Atributos
  private final PedidoGateway pedidoGateway;
  private final ClienteGateway clienteGateway;
  private final ProdutoGateway produtoGateway;

  /**
   * Construtor público de PedidoController.
   *
   * @param pedidoGateway O gateway do repositório de pedidos.
   * @param clienteGateway O gateway do repositório de clientes.
   * @param produtoGateway O gateway do repositório de produtos.
   */
  @Autowired
  public PedidoControllerImpl(PedidoGateway pedidoGateway, ClienteGateway clienteGateway, 
      ProdutoGateway produtoGateway) {

    this.pedidoGateway = pedidoGateway;
    this.clienteGateway = clienteGateway;
    this.produtoGateway = produtoGateway;
  }

  // Métodos públicos
  @Override
  public ResponseEntity<StatusPedidoDto> fazerCheckout(PedidoDto pedidoDto)
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException {
        
    Cliente cliente = getClientePedidoDto(pedidoDto);
    List<ItemPedido> itens = getItensPedidoDto(pedidoDto);

    Pedido pedido = new Pedido(cliente, itens);
    pedido = FazerCheckoutPedido.fazerCheckout(pedidoGateway, pedido);

    return StatusPedidoResponseAdapter.adaptar(pedido, HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<StatusPedidoDto> atualizarStatusPedido(Long numeroPedido)
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException {
        
    Pedido pedido = AtualizarStatusPedido.atualizar(pedidoGateway, numeroPedido);
    return StatusPedidoResponseAdapter.adaptar(pedido, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<StatusPagamentoDto> consultarStatusPagamento(Long numeroPedido)
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException {
        
    Pedido pedido = BuscarPedido.buscar(pedidoGateway, numeroPedido);
    return StatusPagamentoResponseAdapter.adaptar(pedido, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<Pedido>> listarPedidos()
      throws BusinessRuleException, ResourceNotFoundException {
        
    List<Pedido> pedidos = ListarPedidos.listar(pedidoGateway);
    if (pedidos.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return PedidoResponseAdapter.adaptar(pedidos, HttpStatus.OK);
    }
  }

  @Override
  public ResponseEntity<Void> webhookMercadoPago(PagamentoDto pagamentoDto)
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException {

    var statusPagamento = PagamentoRequestAdapter.adaptar(pagamentoDto);
    AtualizarStatusPagamento.atualizar(pedidoGateway, statusPagamento);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  // Métodos privados
  private Cliente getClientePedidoDto(PedidoDto pedidoDto)
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException {

    Cliente cliente = null;
    Long cpfLong = pedidoDto.getCpfCliente();

    if (cpfLong != null) {
      Cpf cpf = new Cpf(cpfLong);
      cliente = BuscarClientePeloCpf.buscar(clienteGateway, cpf);
    }
    return cliente;
  }

  private List<ItemPedido> getItensPedidoDto(PedidoDto pedidoDto)
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException  {

    List<Produto> produtos = new ArrayList<>();
    List<ItemPedidoDto> itensDto = pedidoDto.getItens();

    for (ItemPedidoDto item : itensDto) {
      var produto = BuscarProduto.buscar(produtoGateway, item.codigoProduto);
      produtos.add(produto);
    }
    return ItemPedidoRequestAdapter.adaptar(itensDto, produtos);
  }

}
