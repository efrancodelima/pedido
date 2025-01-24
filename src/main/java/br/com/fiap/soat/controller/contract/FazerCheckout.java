package br.com.fiap.soat.controller.contract;

import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
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
public interface FazerCheckout {

  /**
   * Endpoint para o checkout de pedidos.
   *
   * @param pedidoDto O pedido para o checkout.
   * @return Um objeto do tipo ResponseEntity contendo o status do pedido,
   *     em caso de sucesso, ou a mensagem de erro, em caso de falha.
   */
  @Operation(summary = "Fazer checkout", description = Constantes.DESC_FAZER_CHECKOUT)
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
        examples = @ExampleObject(value = Constantes.EXAMPLE_BAD_REQUEST))),

      @ApiResponse(
        responseCode = Constantes.CODE_NOT_FOUND,
        description = Constantes.DESC_NOT_FOUND,
        content = @Content(mediaType = "application/json",
        examples = @ExampleObject(value = Constantes.EXAMPLE_NOT_FOUND))),

      @ApiResponse(
        responseCode = Constantes.CODE_BAD_GATEWAY,
        description = Constantes.DESC_BAD_GATEWAY,
        content = @Content(mediaType = "application/json",
        examples = @ExampleObject(value = Constantes.EXAMPLE_BAD_GATEWAY)))
  })

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
    public static final String DESC_CREATED = "Created";
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

    public static final String CODE_BAD_REQUEST = "400";
    public static final String DESC_BAD_REQUEST = "Bad Request";
    public static final String EXAMPLE_BAD_REQUEST = """
        {
          "status": 400,
          "body": {
            "data": null,
            "errorMsg": "O pedido deve conter, pelo menos, um item."
          }
        }
        """;
        
    public static final String CODE_NOT_FOUND = "404";
    public static final String DESC_NOT_FOUND = "Not found";
    public static final String EXAMPLE_NOT_FOUND = """
        {
          "status": 404,
          "body": {
            "data": null,
            "errorMsg": "Não foi encontrado nenhum produto para o código informado."
          }
        }
        """;
    
    public static final String CODE_BAD_GATEWAY = "502";
    public static final String DESC_BAD_GATEWAY = "Bad Gateway";
    public static final String EXAMPLE_BAD_GATEWAY = """
        {
          "status": 502,
          "body": {
            "data": null,
            "errorMsg": "Erro na comunicação com o sistema de pagamentos."
          }
        }
        """;
  }
}

