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
 * Interface da API Produto, rota para buscar um ou mais produtos.
 */
@Tag(name = "Produto")
public interface BuscarProdutos {

  /**
   * Buscar um ou mais produtos.
   *
   * @param codigoProdutos Uma lista com os códigos dos produtos a serem buscados.
   * @return Um objeto contendo a lista dos produtos encontrados,
   *     em caso de sucesso, ou a mensagem de erro, em caso de falha.
   */
  @Operation(summary = "Buscar produtos pelos códigos", description = Constantes.DESCRICAO)
  
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
  
  @Parameter(name = "codigos", description = "Uma lista com os códigos dos produtos",
      required = true, example = "1, 2, 3")
  
  @GetMapping(value = "/buscar/{codigos}")

  ResponseEntity<ResponseWrapper<List<ProdutoJpa>>>
      buscarProdutos(@PathVariable("codigos") List<Long> codigoProdutos);

  /** 
   * Constantes utilizadas pela interface.
   */
  final class Constantes {

    private Constantes() {}

    public static final String DESCRICAO = "Para realizar a busca, "
        + "informe os códigos dos produtos em uma lista. O retorno será uma lista com os produtos "
        + "na mesma ordem em que foram informados os respectivos códigos. Caso algum produto não "
        + "seja encontrado, será retornado null (apenas para aquele produto).";
    
    public static final String CODE_OK = "200";
    public static final String DESC_OK = "Ok";
    public static final String EXAMPLE_OK = """
        {
          "data": [
            null,
            {
                "codigo": 1,
                "nome": "X-Monstrão",
                "descricao": "O lanche do marombeiro",
                "preco": 34.99,
                "categoria": "LANCHE"
            },
            {
                "codigo": 2,
                "nome": "Smoothie de Açaí",
                "descricao": "Açaí batido coberto com creme de maracujá",
                "preco": 19.99,
                "categoria": "BEBIDA"
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
          "errorMsg": "O código informado para o produto é inválido."
        }
        """;

  }
}
