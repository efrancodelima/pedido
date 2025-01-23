package br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamentoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe StatusPagamentoDto.
 */
@Data
@NoArgsConstructor
public class StatusPagamentoDto {

  @Schema(description = "Número do pedido.", example = "123")
  public Long numeroPedido;

  @Schema(description = "Status do pagamento.", example = "APROVADO")
  public StatusPagamentoEnum status;

  @Schema(description = "Data e hora da última atualização do pagamento.",
      example = "2024-09-08 09:10:00")
  public String dataHora;

}
