package br.com.fiap.techchallenge.interfacelayer.gateways.entities;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamentoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade JPA StatusPagamento.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class StatusPagamentoJpa implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "codigo_pagamento", nullable = true)
  private Long codigo;

  @Enumerated(EnumType.STRING)
  @Column(name = "status_pagamento", nullable = false)
  private StatusPagamentoEnum status;

  @Column(name = "timestamp_status_pagamento", nullable = false)
  private LocalDateTime dataHora;

}
