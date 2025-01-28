package br.com.fiap.soat.dto.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO usado na requisição do controller FazerCheckout.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoDto {

  @Schema(description = "Código do produto.", example = "1")
  private Long codigoProduto;

  @Schema(description = "Quantidade.", example = "1")
  private Integer quantidade;
}
