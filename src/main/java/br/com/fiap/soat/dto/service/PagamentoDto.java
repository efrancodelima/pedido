package br.com.fiap.soat.dto.service;

import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe PagamentoDto.
 * DTO usado na resposta do service NovoPagamentoService.
 */
@Data
@NoArgsConstructor
public class PagamentoDto {

  public Long codigoPagamento;

  public Long numeroPedido;

  public BigDecimal valor;

  public String status;

  public String timestamp;
}
