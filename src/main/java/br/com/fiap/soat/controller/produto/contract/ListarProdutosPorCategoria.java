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

  /**
   * Listar produtos por categoria.
   *
   * @param categoria A categoria dos produtos a serem listados.
   * @return Um objeto contendo a lista dos produtos encontrados,
   *     em caso de sucesso, ou a mensagem de erro, em caso de falha.
   */
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

  @Parameter(name = "categoria", description = "A categoria dos produtos a serem listados",
      required = true, example = "lanche")

  @GetMapping(value = "/listar/{categoria}")

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
              "codigo": 1,
              "nome": "Produto 1",
              "descricao": "Descrição do Produto 1",
              "preco": 10.50,
              "categoria": "Categoria1"
            },
            {
              "codigo": 2,
              "nome": "Produto 2",
              "descricao": "Descrição do Produto 2",
              "preco": 20.75,
              "categoria": "Categoria2"
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
