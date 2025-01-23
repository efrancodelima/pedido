package br.com.fiap.soat.entity;

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
 * Entidade JPA StatusPedido.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class StatusPedidoJpa implements Serializable {

  private static final long serialVersionUID = 1L;

  @Enumerated(EnumType.STRING)
  @Column(name = "status_pedido", nullable = false)
  private StatusPedidoEnum status;

  @Column(name = "timestamp_status_pedido", nullable = false)
  private LocalDateTime dataHora;

}
