package br.com.fiap.soat.service.provider.pedido;

import br.com.fiap.soat.entity.PedidoJpa;
import br.com.fiap.soat.exception.BadRequestException;
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
  
  @Autowired
  public BuscarPedidosService(PedidoRepository repository) {
    this.repository = repository;
  }

  public List<PedidoJpa> execute(List<Long> numerosPedidos) throws BadRequestException {

    CodigoValidator.validar(numerosPedidos, PedidoJpa.class);

    var listaPedidos = new ArrayList<PedidoJpa>();

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
