package br.com.fiap.soat.controller.pedido.contract;

import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.dto.controller.request.PedidoDto;
import br.com.fiap.soat.dto.service.response.RegistroProducaoDto;
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
@Tag(name = "Pedido")
public interface FazerCheckout {

  @Operation(summary = "Fazer checkout", description = Constantes.DESCRICAO)
  
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

  ResponseEntity<ResponseWrapper<RegistroProducaoDto>>
      fazerCheckout(@RequestBody PedidoDto pedidoDto);

  /** 
   * Constantes usadas pela interface.
   */
  final class Constantes {

    private Constantes() {}

    public static final String DESCRICAO = "Para realizar o checkout do pedido, "
        + "informe os dados do pedido conforme o schema PedidoDto no final desta página.<br>"
        + "O CPF do cliente é opcional, deixe em branco se não quiser identificar o cliente.";
    
    public static final String CODE_CREATED = "201";
    public static final String DESC_CREATED = "Created";
    public static final String EXAMPLE_CREATED = """
        {
          "data": {
            "numeroPedido": 1,
            "status": "RECEBIDO",
            "dataHora": "2025-01-20 15:30:00"
          },
          "errorMsg": null
        }
        """;

    public static final String CODE_BAD_REQUEST = "400";
    public static final String DESC_BAD_REQUEST = "Bad Request";
    public static final String EXAMPLE_BAD_REQUEST = """
        {
          "data": null,
          "errorMsg": "O pedido deve conter, pelo menos, um item."
        }
        """;
        
    public static final String CODE_NOT_FOUND = "404";
    public static final String DESC_NOT_FOUND = "Not found";
    public static final String EXAMPLE_NOT_FOUND = """
        {
          "data": null,
          "errorMsg": "Nenhum produto foi encontrado para o código informado."
        }
        """;
    
    public static final String CODE_BAD_GATEWAY = "502";
    public static final String DESC_BAD_GATEWAY = "Bad Gateway";
    public static final String EXAMPLE_BAD_GATEWAY = """
        {
          "data": null,
          "errorMsg": "Erro na comunicação com o sistema de pagamentos."
        }
        """;
  }
}
