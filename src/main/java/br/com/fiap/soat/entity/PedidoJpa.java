package br.com.fiap.soat.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade JPA Pedido.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedido")
public class PedidoJpa implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long numero;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "codigo_cliente", nullable = true)
  private ClienteJpa clienteJpa;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "itens_pedido", joinColumns = @JoinColumn(name = "numero_pedido"))
  private List<ItemPedidoJpa> itensJpa;

  @Column(name = "timestamp_checkout", nullable = true)
  private LocalDateTime timestampCheckout;

}
