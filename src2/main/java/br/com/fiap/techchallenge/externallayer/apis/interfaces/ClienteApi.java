package br.com.fiap.techchallenge.externallayer.apis.interfaces;

import br.com.fiap.techchallenge.applicationlayer.exceptions.ApplicationException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.ResourceNotFoundException;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.ClienteDto;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Interface da API Cliente.
 */
@Tag(name = "Clientes")
public interface ClienteApi {

  /**
   * Cadastrar cliente.
   *
   * @param cadastrar O cliente a ser cadastrado.
   * @return O cliente cadastrado.
   * @throws ApplicationException Exceção da aplicação lançada pelo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   */
  @Operation(summary = "Cadastrar cliente", description = Constantes.DESC_CADASTRAR)
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = Constantes.D201, 
      content = @Content(mediaType = "application/json", examples = @ExampleObject(
        value = Constantes.E201))),

    @ApiResponse(responseCode = "400", description = Constantes.D400,
      content = @Content(mediaType = "application/json", examples = @ExampleObject(
        value = Constantes.E400))),
    
    @ApiResponse(responseCode = "422", description = Constantes.D422,
      content = @Content(mediaType = "application/json", examples = @ExampleObject(
        value = Constantes.E422))),
    
    @ApiResponse(responseCode = "500", description = Constantes.D500,
      content = @Content(mediaType = "application/json", examples = @ExampleObject(
        value = Constantes.E500))) })

  @PostMapping(value = "/cadastrar")

  ResponseEntity<Cliente>
      cadastrarCliente(@RequestBody ClienteDto cadastrar)
      throws ApplicationException, BusinessRuleException;

  /**
   * Buscar cliente por cpf.
   *
   * @param cpf O CPF do cliente a ser buscado.
   * @return O cliente encontrado para o CPF.
   * @throws ApplicationException Exceção da aplicação lançada pelo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   * @throws ResourceNotFoundException Exceção de recurso não encontrado lançada pelo método.
   */
  @Operation(summary = "Buscar cliente por CPF", description = Constantes.DESC_BUSCAR)
  @Parameter(name = "cpf", description = "CPF do cliente", required = true)
  
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = Constantes.D200,
      content = @Content(mediaType = "application/json", examples = @ExampleObject(
        value = Constantes.E200))),

    @ApiResponse(responseCode = "400", description = Constantes.D400,
      content = @Content(mediaType = "application/json", examples = @ExampleObject(
        value = Constantes.E400))),

    @ApiResponse(responseCode = "404", description = Constantes.D404,
      content = @Content(mediaType = "application/json", examples = @ExampleObject(
        value = Constantes.E404))),

    @ApiResponse(responseCode = "422", description = Constantes.D422,
      content = @Content(mediaType = "application/json", examples = @ExampleObject(
        value = Constantes.E422))),

    @ApiResponse(responseCode = "500", description = Constantes.D500,
      content = @Content(mediaType = "application/json", examples = @ExampleObject(
        value = Constantes.E500))) })
  
  @GetMapping(value = "/buscar/{cpf}")

  ResponseEntity<Cliente>
      buscarClientePorCpf(@PathVariable("cpf") long cpf)
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException;

  /** 
   * Constantes usadas na interface da API Cliente.
   */
  final class Constantes {

    private Constantes() {}

    public static final String DESC_CADASTRAR = "Para cadastrar um cliente, "
        + "informe os dados do cliente conforme o schema ClienteDto no final desta página."
        + "<br>O nome e o email do cliente são opcionais, mas pelo menos um dos dois precisa "
        + "ser informado.";
    
    public static final String DESC_BUSCAR = "Para buscar um cliente, informe o CPF "
        + "(somente números, sem pontos e traço).";

    public static final String D200 = "Sucesso!";
    public static final String D201 = D200;
    public static final String D400 = "Requisição inválida!";
    public static final String D404 = "Cliente não encontrado!";
    public static final String D422 = "Operação não permitida!";
    public static final String D500 = "Erro!";

    public static final String E200 = "{ \"codigo\": 1, \"cpf\": { \"digitoVerificador\": 96, "
        + "\"numeroSemDigito\": 111222333 }, \"nome\": \"Arthur Conan Doyle\", \"email\": "
        + "\"conanad@gmail.com\" }";

    public static final String E201 = E200;

    public static final String E400 = "{ \"timestamp\": \"2024-09-08T02:05:58.036+00:00\", "
        + "\"status\": 400, \"error\": \"Bad Request\", \"path\": \"/clientes/cadastrar\" }";

    public static final String E404 = "{ \"message\": \"Nenhum cliente foi encontrado para o CPF "
        + "informado.\" }";

    public static final String E422 = "{ \"message\": \"O número do CPF deve ser maior que 0.\" }";
    public static final String E500 = "{ \"message\": \"Ocorreu um erro inesperado no "
        + "servidor.\" }";
  }

}
