package br.com.fiap.soat.dto.service.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO usado na resposta do service NotificarProducaoService.
 */
@Data
@NoArgsConstructor
public class RegistroProducaoDto {

  @Schema(description = "Número do pedido.", example = "123")
  public Long numeroPedido;

  @Schema(description = "Status do pedido.", example = "RECEBIDO")
  public String status;

  @Schema(description = "Timestamp da última atualização do pedido.",
      example = "2025-01-20 09:10:00")
  public String timestamp;

}
