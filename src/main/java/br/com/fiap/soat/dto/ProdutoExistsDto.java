package br.com.fiap.soat.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe ValidarProdutoDto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoExistsDto {

  @Schema(description = "Código do produto.", example = "1")
  private Long codigo;
  
  @Schema(description = "Existência do produto.", example = "true")
  private boolean exists;
}
