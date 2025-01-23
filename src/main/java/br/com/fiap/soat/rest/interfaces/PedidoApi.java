package br.com.fiap.soat.rest.interfaces;

import br.com.fiap.soat.dto.PagamentoDto;
import br.com.fiap.soat.dto.StatusPagamentoDto;
import br.com.fiap.soat.dto.StatusPedidoDto;
import br.com.fiap.soat.dto.pedido.PedidoDto;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.BusinessRuleException;
import br.com.fiap.soat.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Interface da API Pedidos.
 */
@Tag(name = "Pedidos")
public interface PedidoApi {

  /**
   * Fazer checkout.
   *
   * @param pedidoDto O pedido para o checkout.
   * @return O número, status e timestamp do pedido.
   * @throws BadRequestException Exceção da aplicação lançada pelo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   * @throws ResourceNotFoundException Exceção de recurso não encontrado lançada pelo método.
   */
  @Operation(summary = "Fazer checkout", description = Constantes.DESC_FAZER_CHECKOUT)
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = Constantes.D201,
      content = @Content(mediaType = "application/json", examples = @ExampleObject(
        value = Constantes.E201))),

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

  ResponseEntity<StatusPedidoDto>
      fazerCheckout(@RequestBody PedidoDto pedidoDto)
      throws BadRequestException, BusinessRuleException, ResourceNotFoundException;

  /**
   * Atualizar status do pedido.
   *
   * @param numeroPedido O número do pedido.
   * @return O número, status e timestamp do pedido.
   * @throws BadRequestException Exceção da aplicação lançada pelo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   * @throws ResourceNotFoundException Exceção de recurso não encontrado lançada pelo método.
   */
  @Operation(summary = "Atualizar o status do pedido", description = 
      Constantes.DESC_ATUALIZAR_STATUS_PEDIDO)
  
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

  @PutMapping(value = "/status/{numeroPedido}")

  ResponseEntity<StatusPedidoDto> 
      atualizarStatusPedido(@PathVariable("numeroPedido") long numeroPedido)
      throws BadRequestException, BusinessRuleException, ResourceNotFoundException;

  /**
   * Consultar status do pagamento.
   *
   * @param numeroPedido O número do pedido.
   * @return O status do pagamento.
   * @throws BadRequestException Exceção da aplicação lançada pelo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   * @throws ResourceNotFoundException Exceção de recurso não encontrado lançada pelo método.
   */
  @Operation(summary = "Consultar o status do pagamento",
      description = Constantes.DESC_CONSULTAR_STATUS_PAG)
  
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
  
  @GetMapping(value = "/pagamento/{numeroPedido}")

  ResponseEntity<StatusPagamentoDto> 
      consultarStatusPagamento(@PathVariable("numeroPedido") long numeroPedido)
      throws BadRequestException, BusinessRuleException, ResourceNotFoundException;

  /**
   * Listar pedidos.
   *
   * @return A lista com os pedidos ordenados por situação.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   * @throws ResourceNotFoundException Exceção de recurso não encontrado lançada pelo método.
   */
  @Operation(summary = "Listar pedidos", description = Constantes.DESC_LISTAR_PEDIDOS)
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = Constantes.D200,
      content = @Content(mediaType = "application/json", examples = @ExampleObject(
        value = Constantes.E200_LISTAR))),

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

  @GetMapping(value = "/listar")

  ResponseEntity<List<PedidoDto>> listarPedidos()
      throws BusinessRuleException, ResourceNotFoundException;

  /**
   * Webhook do Mercado Pago.
   *
   * @param pagamentoDto A notificação do Mercado Pago.
   * @throws BadRequestException Exceção da aplicação lançada pelo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   * @throws ResourceNotFoundException Exceção de recurso não encontrado lançada pelo método.
   */
  @Hidden
  @PutMapping(value = "/webhook/")
  ResponseEntity<Void> webhookMercadoPago(@RequestBody PagamentoDto pagamentoDto)
      throws BadRequestException, BusinessRuleException, ResourceNotFoundException;

  /** 
   * Constantes usadas na interface da API Pedidos.
   */
  final class Constantes {

    private Constantes() {}

    public static final String DESC_FAZER_CHECKOUT = "Para realizar o checkout do pedido, "
        + "informe os dados do pedido conforme o schema PedidoDto no final desta página.<br>"
        + "O CPF do cliente é opcional, deixe em branco se não quiser identificar o cliente.";
    
    public static final String DESC_ATUALIZAR_STATUS_PEDIDO = "Para atualizar o status do pedido, "
        + "informe o número do pedido pelo path.<br>Os status possuem uma ordem sequencial, então "
        + "ele mudará automaticamente para o valor seguinte.";
    
    public static final String DESC_CONSULTAR_STATUS_PAG = "Para consultar o status do pagamento, "
        + "informe o número do pedido pelo path.";
    
    public static final String DESC_LISTAR_PEDIDOS = "Lista os pedidos recebidos, em preparação e "
        + "prontos.<br>Os pedidos mais antigos são exibidos primeiro e os mais novos depois.<br>"
        + "A lista também é ordenada pelo status do pedido: pedidos prontos no topo da lista e "
        + "recebidos no final.";
  
    public static final String D200 = "Sucesso!";
    public static final String D201 = D200;
    public static final String D204 = D200;
    public static final String D400 = "Requisição inválida!";
    public static final String D404 = "Pedido não encontrado!";
    public static final String D422 = "Operação não permitida!";
    public static final String D500 = "Erro!";
  
    public static final String E200 = "{ \"numeroPedido\": 4, \"statusPedido\": \"PRONTO\", "
        + "\"dataHora\": \"2024-09-08 15:31:59\" }";
    
    public static final String E200_LISTAR = "[ { \"numero\": 1, \"cliente\": null, \"itens\": "
        + "[ { \"produto\": { \"codigo\": 1, \"nome\": \"X-Monstrão\", \"descricao\": null, "
        + "\"preco\": 20, \"categoria\": \"LANCHE\" }, \"quantidade\": 1, \"valorItem\": 20 } ], "
        + "\"dataHoraCheckout\": \"2024-09-20T10:22:09.175124\", \"statusPagamento\": { "
        + "\"codigo\\\": 0, \"status\": \"AGUARDANDO_PAGAMENTO\", "
        + "\"dataHora\": \"2024-09-20T10:22:09.246543\" }, "
        + "\"statusPedido\": { \"status\": \"RECEBIDO\", "
        + "\"dataHora\": \"2024-09-20T10:22:09.175173\" }, "
        + "\"valorPedido\": 20 }, { \"numero\": 2, \"cliente\": null, \"itens\": [ { "
        + "\"produto\": { \"codigo\": 2, \"nome\": \"X-Monstrinho\", \"descricao\": null, "
        + "\"preco\": 20, \"categoria\": \"LANCHE\" }, \"quantidade\": 2, \"valorItem\": 40 } ], "
        + "\"dataHoraCheckout\": \"2024-09-20T10:22:15.888514\", "
        + "\"statusPagamento\": { \"codigo\": 0, \"status\": \"AGUARDANDO_PAGAMENTO\", "
        + "\"dataHora\": \"2024-09-20T10:22:15.899493\" }, \"statusPedido\": { "
        + "\"status\": \"RECEBIDO\", \"dataHora\": \"2024-09-20T10:22:15.888543\" }, "
        + "\"valorPedido\": 40 }]";
    
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

