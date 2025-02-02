package br.com.fiap.soat.service.provider.pedido;

import br.com.fiap.soat.dto.controller.request.PedidoDto;
import br.com.fiap.soat.dto.service.request.CriarPagamentoDto;
import br.com.fiap.soat.dto.service.response.RegistroProducaoDto;
import br.com.fiap.soat.exception.BadGatewayException;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.mapper.pedido.PedidoMapper;
import br.com.fiap.soat.repository.PedidoRepository;
import br.com.fiap.soat.service.consumer.NotificarPagamentoService;
import br.com.fiap.soat.service.consumer.NotificarProducaoService;
import br.com.fiap.soat.validator.pedido.PedidoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Service para fazer o checkout do pedido.
 */
@Component
public class FazerCheckoutService {

  private final PedidoRepository pedidoRepository;
  private final PedidoMapper mapper;
  private final NotificarProducaoService notificarProducaoService;
  private final NotificarPagamentoService notificarPagamentoService;

  @Autowired
  public FazerCheckoutService(PedidoRepository repository, PedidoMapper mapper, 
      NotificarProducaoService producaoService, NotificarPagamentoService pagamentoService) {

    this.pedidoRepository = repository;
    this.mapper = mapper;
    this.notificarProducaoService = producaoService;
    this.notificarPagamentoService = pagamentoService;
  }

  public RegistroProducaoDto execute(PedidoDto pedidoDto)
      throws BadGatewayException, BadRequestException, NotFoundException {

    PedidoValidator.validar(pedidoDto);

    var pedido = mapper.toEntity(pedidoDto);

    pedido = pedidoRepository.save(pedido);

    var requisicao = new CriarPagamentoDto(pedido.getNumero(), pedido.getValor());
    notificarPagamentoService.execute(requisicao);
    
    return notificarProducaoService.execute(pedido.getNumero());
  }
}
