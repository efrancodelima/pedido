package br.com.fiap.soat.dto.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe ClienteDto.
 * DTO usado na requisição do controller CadastrarCliente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {

  @Schema(description = "CPF do cliente.", example = "11122233396")
  private Long cpf;

  @Schema(description = "Nome do cliente.", example = "Arthur Conan Doyle")
  private String nome;

  @Schema(description = "Email do cliente.", example = "conanad@gmail.com")
  private String email;

}
