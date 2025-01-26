package br.com.fiap.soat.dto.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe PedidoDto.
 * DTO usado na requisição do controller FazerCheckout.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDto {

  @Schema(description = "Código do cliente (opcional).", example = "1")
  private Long codigoCliente;

  @Schema(description = "Itens do pedido.", example = "[{\"codigoProduto\": 1, \"quantidade\": 1}]")
  private List<ItemPedidoDto> itens;
}
