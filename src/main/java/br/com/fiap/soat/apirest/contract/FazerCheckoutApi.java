package br.com.fiap.soat.apirest.contract;

import br.com.fiap.soat.apirest.wrapper.ResponseWrapper;
import br.com.fiap.soat.dto.PedidoDto;
import br.com.fiap.soat.dto.StatusPedidoDto;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.NotFoundException;
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
 * Interface da API Pedidos, rota para fazer checkout.
 */
@Tag(name = "Pedidos")
public interface FazerCheckoutApi {

  /**
   * Fazer checkout do pedido.
   *
   * @param pedidoDto O pedido para o checkout.
   * @return Um objeto do tipo ResponseEntity contendo o status do pedido,
   *     em caso de sucesso, ou a mensagem de erro, em caso de falha.
   */
  @Operation(summary = "Fazer checkout", description = Constantes.DESC_FAZER_CHECKOUT)
  @ApiResponses(value = {
    @ApiResponse(responseCode = Constantes.CODE_CREATED, description = Constantes.DESC_CREATED,
      content = @Content(mediaType = "application/json", examples = @ExampleObject(
        value = Constantes.EXAMPLE_CREATED))),

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

  @PostMapping(value = "/checkout/")

  ResponseEntity<ResponseWrapper<StatusPedidoDto>>
      fazerCheckout(@RequestBody PedidoDto pedidoDto)
      throws BadRequestException, NotFoundException;

  /** 
   * Constantes usadas na interface da API Pedidos.
   */
  final class Constantes {

    private Constantes() {}

    public static final String DESC_FAZER_CHECKOUT = "Para realizar o checkout do pedido, "
        + "informe os dados do pedido conforme o schema PedidoDto no final desta página.<br>"
        + "O CPF do cliente é opcional, deixe em branco se não quiser identificar o cliente.";
    
    public static final String CODE_CREATED = "201";
    public static final String DESC_CREATED = "Sucesso!";
    public static final String EXAMPLE_CREATED = """
        {
          "status": 200,
          "body": {
            "data": {
              "numeroPedido": 1,
              "status": "RECEBIDO",
              "dataHora": "2025-01-20 15:30:00"
            },
            "errorMsg": null
          }
        }
        """;

    public static final String D200 = "Sucesso!";
    public static final String D201 = D200;
    public static final String D204 = D200;
    public static final String D400 = "Requisição inválida!";
    public static final String D404 = "Pedido não encontrado!";
    public static final String D422 = "Operação não permitida!";
    public static final String D500 = "Erro!";
  
    public static final String E200 = "{ \"numeroPedido\": 1, \"statusPedido\": \"RECEBIDO\", "
        + "\"dataHora\": \"2025-01-20 15:30:00\" }";
    
    public static final String E200_LISTAR = "[ { \"data\": null, "
        + "\"errorMsg\": \"O pedido deve conter, pelo menos, um item.\"";
    
    public static final String E201 = E200;
    public static final String E204 = "";
  
    public static final String E400 = "{ \"code\": 400, \"status\": \"Bad Request\", "
        + "\"message\": \"JSON parse error: Cannot deserialize value of type `java.lang.Long` "
        + "from String \\\"x\\\": not a valid `java.lang.Long` value\", "
        + "\"timestamp\": \"2024-09-08T15:39:12.091139608\" }";
    
    public static final String E404 = "{ \"code\": 404, \"status\": \"Not Found\", "
        + "\"message\": \"Não foi encontrado nenhum pedido para o número informado.\", "
        + "\"timestamp\": \"2024-09-08T15:37:05.129042146\"}";
    
    public static final String E422 = "{ \"code\": 422, \"status\": \"Unprocessable Entity\", "
        + "\"message\": \"O pedido precisa conter, pelo menos, um item.\", "
        + "\"timestamp\": \"2024-09-08T15:37:05.129042146\"}";
    
    public static final String E500 = "{ \"code\": 500, \"status\": \"Internal Server Error\", "
        + "\"message\": \"Ocorreu um erro inesperado no servidor.\", "
        + "\"timestamp\": \"2024-09-08T15:37:05.129042146\"}";
  

  }
}

