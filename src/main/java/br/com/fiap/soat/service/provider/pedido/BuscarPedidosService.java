package br.com.fiap.soat.service.provider.pedido;

import br.com.fiap.soat.entity.PedidoJpa;
import br.com.fiap.soat.exception.BadGatewayException;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.repository.PedidoRepository;
import br.com.fiap.soat.validator.produto.CodigoValidator;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Service para buscar um ou mais pedidos.
 */
@Component
public class BuscarPedidosService {

  private final PedidoRepository repository;
  
  /**
   * O construtor público do service.
   *
   * @param repository O repositório para acesso ao banco de dados.
   */
  @Autowired
  public BuscarPedidosService(PedidoRepository repository) {
    this.repository = repository;
  }

  /**
   * Buscar um ou mais pedidos.
   *
   * @param numerosPedidos Uma lista com os números dos pedidos a serem buscados.
   * @return Uma lista com os pedidos encontrados.
   * @throws BadRequestException Exceção do tipo bad request lançada pelo método.
   * @throws BadGatewayException Exceção do tipo bad gateway lançada pelo método.
   * @throws NotFoundException Exceção do tipo not found lançada pelo método.
   */
  public List<PedidoJpa> execute(List<Long> numerosPedidos)
      throws BadGatewayException, BadRequestException, NotFoundException {

    CodigoValidator.validar(numerosPedidos, PedidoJpa.class);

    List<PedidoJpa> listaPedidos = new ArrayList<>();

    for (var numero : numerosPedidos) {

      var pedidoOpt = repository.findById(numero);

      if (pedidoOpt.isPresent()) {
        listaPedidos.add(pedidoOpt.get());
      
      } else {
        listaPedidos.add(null);
      }
    }
    
    return listaPedidos;
  }
}
