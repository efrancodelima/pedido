package br.com.fiap.soat.dto.service;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO usado na resposta do service CriarPagamentoService.
 */
@Data
@NoArgsConstructor
public class PagamentoDto {

  @Schema(description = "Código do pagamento.", example = "123")
  public Long codigoPagamento;

  @Schema(description = "Número do pedido.", example = "123")
  public Long numeroPedido;

  @Schema(description = "Valor do pedido/pagamento.", example = "50.00")
  public BigDecimal valor;

  @Schema(description = "Status do pagamento.", example = "APROVADO")
  public String status;

  @Schema(description = "Timestamp do status.", example = "2025-01-20T14:20:00.000")
  public String timestamp;
}
