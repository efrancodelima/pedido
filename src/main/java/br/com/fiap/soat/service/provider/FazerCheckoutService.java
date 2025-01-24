package br.com.fiap.soat.service.provider;

import br.com.fiap.soat.dto.PedidoDto;
import br.com.fiap.soat.dto.StatusPedidoDto;
import br.com.fiap.soat.exception.BadGatewayException;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.exception.messages.BadGatewayMessage;
import br.com.fiap.soat.mapper.PedidoMapper;
import br.com.fiap.soat.repository.PedidoRepository;
import br.com.fiap.soat.service.consumer.NotificarPagamentoService;
import br.com.fiap.soat.service.consumer.NotificarProducaoService;
import br.com.fiap.soat.validator.PedidoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Service para fazer o checkout do pedido.
 */
@Component
public class FazerCheckoutService {

  private final PedidoRepository repository;
  private final PedidoValidator validator;
  private final PedidoMapper mapper;
  private final NotificarProducaoService notificarProducaoService;
  private final NotificarPagamentoService notificarPagamentoService;

  /**
   * O construtor público do service.
   *
   * @param repository O repositório para acesso ao banco de dados.
   */
  @Autowired
  public FazerCheckoutService(PedidoRepository repository, PedidoValidator validator,
      PedidoMapper mapper, NotificarProducaoService producaoService,
      NotificarPagamentoService pagamentoService) {

    this.repository = repository;
    this.validator = validator;
    this.mapper = mapper;
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

    var pedidoJpa = mapper.toEntity(pedidoDto);

    pedidoJpa = repository.save(pedidoJpa);
    var numeroPedido = pedidoJpa.getNumero();

    // Notifica o sistema de pagamentos que o pedido foi feito
    var responsePag = notificarPagamentoService.execute(numeroPedido);
    if (responsePag.getStatusCode() == HttpStatus.NO_CONTENT) {
      throw new BadGatewayException(BadGatewayMessage.PAGAMENTO);
    }
    
    // Notifica o sistema de produção que o pedido foi feito
    var response = notificarProducaoService.execute(numeroPedido);
    var responseBody = response.getBody();

    if (response.getStatusCode() == HttpStatus.OK && responseBody != null) {
      return responseBody.getData();

    } else {
      throw new BadGatewayException(BadGatewayMessage.PRODUCAO);
    }
  }
  
}
