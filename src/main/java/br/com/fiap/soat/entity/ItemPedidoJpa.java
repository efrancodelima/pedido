package br.com.fiap.soat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade JPA ItemPedido.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ItemPedidoJpa implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "codigo_produto", nullable = false)
  private Long codigoProduto;

  @Column(name = "quantidade", nullable = false)
  private Integer quantidade;

}
