package br.com.fiap.techchallenge.businesslayer.entities.pedido;

import br.com.fiap.techchallenge.businesslayer.constants.Validacao;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.businesslayer.exceptions.messages.PedidoExceptions;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe Pedido.
 */
public class Pedido {
  private Long numero;
  private Cliente cliente;
  private List<ItemPedido> itens;
  private LocalDateTime dataHoraCheckout;
  private StatusPagamento statusPagamento;
  private StatusPedido statusPedido;

  /**
   * Construtor público de Pedido.
   * Usado para novos pedidos.
   *
   * @param cliente O cliente do pedido.
   * @param itens Os itens do pedido.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo construtor.
   */
  public Pedido(Cliente cliente, List<ItemPedido> itens) throws BusinessRuleException {
    
    setCliente(cliente);
    setItens(itens);

    var dataHora = LocalDateTime.now();
    setDataHoraCheckout(dataHora);

    var newStatusPagamento = new StatusPagamento(null, 
        StatusPagamentoEnum.AGUARDANDO_PAGAMENTO, dataHora);

    setStatusPagamento(newStatusPagamento);

    var newStatusPedido = new StatusPedido(StatusPedidoEnum.RECEBIDO, dataHora);

    setStatusPedido(newStatusPedido);
  }

  /**
   * Construtor público de Pedido.
   * Usado para instanciar pedidos que já existem no banco de dados.
   *
   * @param numero O número do pedido.
   * @param cliente O cliente do pedido.
   * @param itens Os itens do pedido.
   * @param dataHoraCheckout A data e hora do checkout do pedido.
   * @param statusPagamento O status do pagamento do pedido.
   * @param statusPedido O status do pedido.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo construtor.
   */
  public Pedido(Long numero, Cliente cliente, 
      List<ItemPedido> itens, LocalDateTime dataHoraCheckout,
      StatusPagamento statusPagamento, StatusPedido statusPedido)
      throws BusinessRuleException {

    setNumero(numero);
    setCliente(cliente);
    setItens(itens);
    setDataHoraCheckout(dataHoraCheckout);
    setStatusPagamento(statusPagamento);
    setStatusPedido(statusPedido);
  }

  // Getters
  public Long getNumero() {
    return numero;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public List<ItemPedido> getItens() {
    return itens;
  }

  public LocalDateTime getDataHoraCheckout() {
    return dataHoraCheckout;
  }

  public StatusPagamento getStatusPagamento() {
    return statusPagamento;
  }

  public StatusPedido getStatusPedido() {
    return statusPedido;
  }

  // Setters
  private void setNumero(Long numero) throws BusinessRuleException {
    validarNumero(numero);
    this.numero = numero;
  }

  private void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  private void setItens(List<ItemPedido> itens) throws BusinessRuleException {
    validarItens(itens);
    this.itens = itens;
  }

  private void setDataHoraCheckout(LocalDateTime dataHoraCheckout)
      throws BusinessRuleException {

    validarDataHoraCheckout(dataHoraCheckout);
    this.dataHoraCheckout = dataHoraCheckout;
  }

  private void setStatusPedido(StatusPedido status) throws BusinessRuleException {
    validarStatus(status);
    this.statusPedido = status;
  }

  // Métodos de validação
  private void validarNumero(Long numero) throws BusinessRuleException {
    if (numero == null) {
      throw new BusinessRuleException(PedidoExceptions.NUMERO_NULO.getMensagem());
    }
    
    if (numero < 1) {
      throw new BusinessRuleException(PedidoExceptions.NUMERO_MIN.getMensagem());
    }
  }

  private void validarItens(List<ItemPedido> itens) throws BusinessRuleException {
    if (itens == null) {
      throw new BusinessRuleException(PedidoExceptions.ITENS_NULO.getMensagem());
    }

    if (itens.isEmpty()) {
      throw new BusinessRuleException(PedidoExceptions.ITENS_VAZIO.getMensagem());
    }

    for (ItemPedido item : itens) {
      validarItem(item);
    }
  }

  private void validarDataHoraCheckout(LocalDateTime dataHora)
      throws BusinessRuleException {

    if (dataHora == null) {
      throw new BusinessRuleException(PedidoExceptions.DATA_CHECKOUT_NULA.getMensagem());
    }

    if (dataHora.toLocalDate().isBefore(Validacao.DATA_MIN)) {
      throw new BusinessRuleException(PedidoExceptions.DATA_CHECKOUT_MIN.getMensagem());
    }

    if (dataHora.isAfter(LocalDateTime.now())) {
      throw new BusinessRuleException(PedidoExceptions.DATA_CHECKOUT_MAX.getMensagem());
    }
  }

  private void validarStatus(StatusPagamento status) throws BusinessRuleException {
    if (status == null) {
      throw new BusinessRuleException(PedidoExceptions.PAGAMENTO_NULO.getMensagem());
    }
  }

  private void validarStatus(StatusPedido status) throws BusinessRuleException {
    if (status == null) {
      throw new BusinessRuleException(PedidoExceptions.STATUS_NULO.getMensagem());
    }
  }

  private void validarItem(ItemPedido item) throws BusinessRuleException {
    if (item == null) {
      throw new BusinessRuleException(PedidoExceptions.ITEM_NULO.getMensagem());
    }
  }

  private void validarPedidoNaoFinalizado() throws BusinessRuleException {
    if (statusPedido.getStatus() == StatusPedidoEnum.FINALIZADO) {
      throw new BusinessRuleException(PedidoExceptions.PEDIDO_FINALIZADO.getMensagem());
    }
  }

  private void validarPagamentoSeRecebido() throws BusinessRuleException {
    boolean pedidoRecebido = this.statusPedido.getStatus() == StatusPedidoEnum.RECEBIDO;
    boolean naoPago = this.statusPagamento.getStatus() != StatusPagamentoEnum.APROVADO;
    if (pedidoRecebido && naoPago) {
      throw new BusinessRuleException(PedidoExceptions.PAGAMENTO_NAO_APROVADO.getMensagem());
    }
  }

  // Outros métodos privados
  private StatusPedidoEnum getNextStatusPedido() {
    StatusPedidoEnum[] statusArray = StatusPedidoEnum.values();
    int posicaoAtual = statusPedido.getStatus().ordinal();
    int proximaPosicao = (posicaoAtual + 1) % statusArray.length;
    return statusArray[proximaPosicao];
  }

  // Métodos públicos
  
  /**
   * Retorna o valor totoal do pedido.
   *
   * @return Valor total do pedido.
   */
  public BigDecimal getValorPedido() {
    BigDecimal result = BigDecimal.valueOf(0);
    for (ItemPedido item : itens) {
      result = result.add(item.getValorItem());
    }
    return result;
  }

  /**
   * Altera o status do pedido.
   *
   * @param status O status do pedido.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   */
  public void setStatusPagamento(StatusPagamento status) throws BusinessRuleException {
    validarStatus(status);

    if (this.statusPagamento != null
        && this.statusPagamento.getStatus() == StatusPagamentoEnum.APROVADO) {

      throw new BusinessRuleException(PedidoExceptions.PAGAMENTO_JA_APROVADO.getMensagem()); 

    } else {
      this.statusPagamento = status;
    }
  }

  /**
   * Atualiza o status do pedido.
   *
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   */
  public void atualizarStatusPedido() throws BusinessRuleException {
    validarPedidoNaoFinalizado();
    validarPagamentoSeRecebido();

    var nextStatus = getNextStatusPedido();
    var novoStatus = new StatusPedido(nextStatus, LocalDateTime.now());
    setStatusPedido(novoStatus);
  }

}
