package br.com.fiap.soat.controller.cliente.contract;

import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.entity.ClienteJpa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Interface da API Clientes, rota para buscar o cliente pelo CPF.
 */
@Tag(name = "Cliente")
public interface BuscarCliente {

  @Operation(summary = "Buscar cliente por CPF", description = Constantes.DESCRICAO)
  
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = Constantes.CODE_OK,
      description = Constantes.DESC_OK,
      content = @Content(mediaType = "application/json",
      examples = @ExampleObject(value = Constantes.EXAMPLE_OK))),

    @ApiResponse(
      responseCode = Constantes.CODE_BAD_REQUEST,
      description = Constantes.DESC_BAD_REQUEST,
      content = @Content(mediaType = "application/json",
      examples = @ExampleObject(value = Constantes.EXAMPLE_BAD_REQUEST))),

    @ApiResponse(
      responseCode = Constantes.CODE_NOT_FOUND,
      description = Constantes.DESC_NOT_FOUND,
      content = @Content(mediaType = "application/json",
      examples = @ExampleObject(value = Constantes.EXAMPLE_NOT_FOUND)))
  })
  
  @GetMapping(value = "/buscar/{cpf}")

  @Parameter(name = "cpf", description = "CPF do cliente", example = "11122233396", required = true)
  
  ResponseEntity<ResponseWrapper<ClienteJpa>>
      buscarClientePorCpf(@PathVariable("cpf") long numeroCpf);

  /** 
   * Constantes utilizadas pela interface.
   */
  final class Constantes {

    private Constantes() {}

    public static final String DESCRICAO = "Para buscar um cliente, informe o CPF "
        + "(somente números, sem pontos e traço).";
    
    public static final String CODE_OK = "200";
    public static final String DESC_OK = "Ok";
    public static final String EXAMPLE_OK = """
        {
          "data": {
            "codigo": 1,
            "cpf": 11122233396,
            "nome": "Arthur Conan Doyle",
            "email": "conanad@gmail.com"
          },
          "errorMsg": null
        }
        """;

    public static final String CODE_BAD_REQUEST = "400";
    public static final String DESC_BAD_REQUEST = "Bad Request";
    public static final String EXAMPLE_BAD_REQUEST = """
        {
          "data": null,
          "errorMsg": "O CPF informado é inválido."
        }
        """;
    
    public static final String CODE_NOT_FOUND = "404";
    public static final String DESC_NOT_FOUND = "Not Found";
    public static final String EXAMPLE_NOT_FOUND = """
        {
          "data": null,
          "errorMsg": "Nenhum cliente foi encontrado para o CPF informado."
        }
        """;
  }
}
