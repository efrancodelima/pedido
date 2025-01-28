package br.com.fiap.soat.service.provider.pedido;

import br.com.fiap.soat.dto.controller.response.PedidoEmProducaoDto;
import br.com.fiap.soat.dto.service.response.RegistroProducaoDto;
import br.com.fiap.soat.entity.PedidoJpa;
import br.com.fiap.soat.exception.BadGatewayException;
import br.com.fiap.soat.repository.PedidoRepository;
import br.com.fiap.soat.service.consumer.ListarItensProducaoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Service para listar os produtos.
 */
@Component
public class ListarPedidosService {

  private final PedidoRepository pedidoRepository;
  private final ListarItensProducaoService producaoService;

  @Autowired
  public ListarPedidosService(PedidoRepository repository,
      ListarItensProducaoService producaoService) {

    this.pedidoRepository = repository;
    this.producaoService = producaoService;
  }

  public List<PedidoEmProducaoDto> execute() throws BadGatewayException {

    var listaRegistrosProducao = producaoService.execute();

    var listaPedidosJpa = buscarPedidos(listaRegistrosProducao);

    return juntar(listaRegistrosProducao, listaPedidosJpa);
  }

  private List<PedidoJpa> buscarPedidos(List<RegistroProducaoDto> listaRegistrosProducao) {

    var listaPedidosJpa = new ArrayList<PedidoJpa>();

    for (var registro : listaRegistrosProducao) {
      
      var pedidoOpt = pedidoRepository.findById(registro.getNumeroPedido());
      if (pedidoOpt.isPresent()) {
        listaPedidosJpa.add(pedidoOpt.get());
      }
    }
    return listaPedidosJpa;
  }

  private List<PedidoEmProducaoDto> juntar(
      List<RegistroProducaoDto> listaRegistros, List<PedidoJpa> listaPedidos) {

    var listaPedidosEmProducao = new ArrayList<PedidoEmProducaoDto>();

    for (int i = 0; i < listaPedidos.size(); i++) {

      var pedidoEmProducao = new PedidoEmProducaoDto(
          listaPedidos.get(i),
          listaRegistros.get(i).getStatus(),
          listaRegistros.get(i).getTimestamp());
      
      listaPedidosEmProducao.add(pedidoEmProducao);
    }

    return listaPedidosEmProducao;
  }
}
