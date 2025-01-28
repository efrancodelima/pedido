package br.com.fiap.soat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade JPA Produto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produto")
public class ProdutoJpa implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long codigo;

  @Column(name = "nome", nullable = false, columnDefinition = "VARCHAR(20)")
  private String nome;

  @Column(name = "descricao", columnDefinition = "VARCHAR(150)")
  private String descricao;

  @Column(name = "preco", nullable = false, columnDefinition = "DECIMAL(5,2)")
  private BigDecimal preco;

  @Enumerated(EnumType.STRING)
  @Column(name = "categoria", nullable = false)
  private CategoriaProduto categoria;

}
