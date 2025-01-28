package br.com.fiap.soat.dto.service;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO usado na requisição do service CriarPagamentoService.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarPagamentoDto {

  @Schema(description = "Número do pedido].", example = "1")
  public Long numeroPedido;

  @Schema(description = "Valor do pedido].", example = "10")
  public BigDecimal valorPedido;
}
