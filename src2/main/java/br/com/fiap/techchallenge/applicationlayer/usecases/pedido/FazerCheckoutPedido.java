package br.com.fiap.techchallenge.applicationlayer.usecases.pedido;

import br.com.fiap.techchallenge.applicationlayer.exceptions.ApplicationException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.ResourceNotFoundException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InPedidoGateway;
import br.com.fiap.techchallenge.applicationlayer.services.Validar;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamento;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamentoEnum;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import java.time.LocalDateTime;

/**
 * Classe FazerCheckoutPedido.
 */
public final class FazerCheckoutPedido {

  private FazerCheckoutPedido() {}

  /**
   * Faz o checkout do pedido.
   *
   * @param gateway O gateway para acesso ao repositório do banco de dados.
   * @param pedido O pedido que será gravado.
   * @return O pedido que foi gravado.
   * @throws ApplicationException Exceção de aplicação lançada pelo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   * @throws ResourceNotFoundException Exceção de recurso não encontrado lançada pelo método.
   */
  public static Pedido fazerCheckout(InPedidoGateway gateway, Pedido pedido)
      throws ApplicationException, BusinessRuleException {

    Validar.notNull(pedido, EnumApplicationExceptions.PEDIDO_NULO);

    pedido = gateway.gravarPedido(pedido);

    // Como estamos mockando a integração com o Mercado Pago
    // vamos deixar o código do pagamento igual ao do pedido
    long codigo = pedido.getNumero();
    var status = StatusPagamentoEnum.AGUARDANDO_PAGAMENTO;
    var dataHora = LocalDateTime.now();

    var pagamento = new StatusPagamento(codigo, status, dataHora);
    pedido.setStatusPagamento(pagamento);
    
    return gateway.gravarPedido(pedido);
  }

}
