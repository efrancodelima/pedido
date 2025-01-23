package br.com.fiap.soat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "codigo_produto", nullable = false)
  private ProdutoJpa produtoJpa;

  @Column(name = "quantidade", nullable = false)
  private Integer quantidade;

}
