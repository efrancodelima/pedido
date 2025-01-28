package br.com.fiap.soat.controller.pedido.contract;

import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.entity.PedidoJpa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Interface da API Pedidos, rota para buscar um ou mais pedidos.
 */
@Tag(name = "Pedido")
public interface BuscarPedidos {

  @Operation(summary = "Buscar pedidos", description = Constantes.DESCRICAO)
  
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
        examples = @ExampleObject(value = Constantes.EXAMPLE_BAD_REQUEST)))
  })

  @GetMapping(value = "/buscar/{numeros}")

  @Parameter(name = "numeros", description = "Uma lista com os números dos produtos",
      required = true, example = "1, 2, 3")
  
  ResponseEntity<ResponseWrapper<List<PedidoJpa>>>
      buscarPedidos(@PathVariable("numeros") List<Long> numerosPedidos);

  /** 
   * Constantes usadas pela interface.
   */
  final class Constantes {

    private Constantes() {}

    public static final String DESCRICAO = "Para realizar o checkout do pedido, "
        + "informe os dados do pedido conforme o schema PedidoDto no final desta página.<br>"
        + "O CPF do cliente é opcional, deixe em branco se não quiser identificar o cliente.";
    
    public static final String CODE_OK = "200";
    public static final String DESC_OK = "Ok";
    public static final String EXAMPLE_OK = """
        {
          "data": [
            {
              "numero": 1,
              "cliente": null,
              "itensJpa": [
                {
                  "produto": {
                    "codigo": 8,
                    "nome": "Sabor Sertanejo",
                    "descricao": "Inspirado na tradição culinária do sertão nordestino",
                    "preco": 34.90,
                    "categoria": "LANCHE"
                  },
                  "quantidade": 2
                }
              ],
              "timestampCheckout": "2025-01-20T15:00:00",
              "valor": 39.90
            }
          ],
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

  }
}
