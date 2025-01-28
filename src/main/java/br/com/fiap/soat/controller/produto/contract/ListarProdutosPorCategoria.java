package br.com.fiap.soat.controller.produto.contract;

import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.entity.ProdutoJpa;
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
 * Interface da API Produto, rota para listar produtos por categoria.
 */
@Tag(name = "Produto")
public interface ListarProdutosPorCategoria {

  @Operation(summary = "Listar produtos por categoria", description = Constantes.DESCRICAO)

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

  @GetMapping(value = "/listar/{categoria}")

  @Parameter(name = "categoria", description = "A categoria dos produtos a serem listados",
      required = true, example = "lanche")

  ResponseEntity<ResponseWrapper<List<ProdutoJpa>>>
      listarProdutosPorCategoria(@PathVariable("categoria") String categoria);

  /** 
   * Constantes utilizadas pela interface.
   */
  final class Constantes {

    private Constantes() {}

    public static final String DESCRICAO = "Para buscar os produtos por categoria, "
        + "informe a categoria (lanche, acompanhamento, bebida ou sobremesa).";

    public static final String CODE_OK = "200";
    public static final String DESC_OK = "Ok";
    public static final String EXAMPLE_OK = """
        {
          "data": [
            {
              "codigo": 21,
              "nome": "Mousse de baunilha",
              "descricao": null,
              "preco": 9.90,
              "categoria": "SOBREMESA"
            },
            {
              "codigo": 35,
              "nome": "Sorvete de brownie",
              "descricao": null,
              "preco": 12.90,
              "categoria": "SOBREMESA"
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
          "errorMsg": "A categoria informada é inválida."
        }
        """;

  }
}
