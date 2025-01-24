package br.com.fiap.soat.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe PedidoDto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDto {

  @Schema(description = "Código do cliente (opcional).", example = "1")
  public Long codigoCliente;

  @Schema(description = "Itens do pedido.",
      example = "[{\"codigoProduto\": 1, \"quantidade\": 2}, {\"codigoProduto\": 2, "
      + "\"quantidade\": 1}]")
  public List<ItemPedidoDto> itens;

}
