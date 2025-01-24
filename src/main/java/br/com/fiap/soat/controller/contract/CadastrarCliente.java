package br.com.fiap.soat.controller.contract;

import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.dto.controller.ClienteDto;
import br.com.fiap.soat.entity.ClienteJpa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Interface da API Clientes, rota para cadastrar cliente.
 */
@Tag(name = "Clientes")
public interface CadastrarCliente {

  /**
   * Endpoint para o cadastro de clientes.
   *
   * @param clienteDto A requisição com os dados do cliente a ser cadastrado.
   * @return Um objeto do tipo ResponseEntity contendo o cliente cadastrado,
   *     em caso de sucesso, ou a mensagem de erro, em caso de falha.
   */
  @Operation(summary = "Cadastrar cliente", description = Constantes.DESC_CADASTRAR)

  @ApiResponses(value = {
    @ApiResponse(
      responseCode = Constantes.CODE_CREATED,
      description = Constantes.DESC_CREATED, 
      content = @Content(mediaType = "application/json",
      examples = @ExampleObject(value = Constantes.EXAMPLE_CREATED))),

    @ApiResponse(
      responseCode = Constantes.CODE_BAD_REQUEST,
      description = Constantes.DESC_BAD_REQUEST,
      content = @Content(mediaType = "application/json",
      examples = @ExampleObject(value = Constantes.EXAMPLE_BAD_REQUEST)))
  })

  @PostMapping(value = "/cadastrar")
        
  ResponseEntity<ResponseWrapper<ClienteJpa>>
      cadastrarCliente(@RequestBody ClienteDto clienteDto);

  /** 
   * Constantes utilizadas pela interface CadastrarClienteApi.
   */
  final class Constantes {

    private Constantes() {}

    public static final String DESC_CADASTRAR = "Para cadastrar um cliente, "
        + "informe os dados do cliente conforme o schema ClienteDto no final desta página."
        + "<br>O nome e o email do cliente são opcionais, mas pelo menos um dos dois precisa "
        + "ser informado.";
    
    public static final String CODE_CREATED = "201";
    public static final String DESC_CREATED = "Created";
    public static final String EXAMPLE_CREATED = """
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
  }

}
