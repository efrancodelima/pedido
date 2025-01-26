package br.com.fiap.soat.dto.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe ProdutoDto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDto {

  @Schema(description = "Nome do produto.", example = "Bomba Gaúcha")
  private String nome;

  @Schema(description = "Descrição do produto (opcional).",
      example = "Cheio de personalidade, inspirado nos sabores fortes "
      + "e marcantes do sul do Brasil.")
  private String descricao;

  @Schema(description = "Preço do produto. Use vírgula no lugar de ponto.", example = "35.90")
  private BigDecimal preco;

  @Schema(description = "Categoria do produto. Valores possíveis: "
      + "lanche, acompanhamento, bebida ou sobremesa.", example = "lanche")
  private String categoria;
}
