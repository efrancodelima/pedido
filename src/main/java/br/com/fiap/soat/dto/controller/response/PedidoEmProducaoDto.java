package br.com.fiap.soat.dto.controller.response;

import br.com.fiap.soat.entity.PedidoJpa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO usado na resposta do controller ListarPedidos.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoEmProducaoDto {

  private PedidoJpa pedido;

  private String status;

  private String timestamp;
}
