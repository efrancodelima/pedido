package br.com.fiap.soat.rest.interfaces;

import br.com.fiap.soat.dto.ProdutoDto;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.BusinessRuleException;
import br.com.fiap.soat.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Interface da API Produtos.
 */
@Tag(name = "Produtos")
public interface ProdutoApi {

  /** 
   * Cadastrar produto.
   *
   * @param cadastrar O produto que será cadastrado.
   * @return O produto que foi cadastrado.
   * @throws BadRequestException Exceção da aplicação lançada pelo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   */
  @Operation(summary = "Cadastrar produto", description = Constantes.DESC_CADASTRAR)
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
  
  @PostMapping("/cadastrar")

  ResponseEntity<ProdutoDto>
      cadastrarProduto(@RequestBody ProdutoDto cadastrar)
      throws BadRequestException, BusinessRuleException;

  /** 
   * Editar produto.
   *
   * @param codigo O código do produto que será atualizado.
   * @param atualizar O produto que será atualizado.
   * @return O produto que foi atualizado.
   * @throws BadRequestException Exceção da aplicação lançada pelo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   * @throws ResourceNotFoundException Exceção de recurso não encontrado lançada pelo método.
   */
  @Operation(summary = "Editar produto", description = Constantes.DESC_EDITAR)
  
  @Parameter(name = "codigo", description = "Código do produto a ser editado", required = true)
  
  @ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = Constantes.D204,
      content = @Content(mediaType = "application/json", examples = @ExampleObject(
        value = Constantes.E204))),
  
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
  
  @PutMapping("/editar/{codigo}")

  ResponseEntity<ProdutoDto>
      editarProduto(@PathVariable long codigo, @RequestBody ProdutoDto atualizar)
      throws BadRequestException, BusinessRuleException, ResourceNotFoundException;

  /** 
   * Remover produto.
   *
   * @param codigo O código do produto que será removido.
   * @return O produto que foi removido.
   * @throws BadRequestException Exceção da aplicação lançada pelo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   * @throws ResourceNotFoundException Exceção de recurso não encontrado lançada pelo método.
   */
  @Operation(summary = "Remover produto", description = Constantes.DESC_REMOVER)

  @Parameter(name = "codigo", description = "Código do produto a ser removido", required = true)

  @ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = Constantes.D204,
      content = @Content(mediaType = "application/json", examples = @ExampleObject(
        value = Constantes.E204))),

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
  
  @DeleteMapping(value = "/remover/{codigo}")

  ResponseEntity<ProdutoDto>
      removerProduto(@PathVariable long codigo)
      throws BadRequestException, BusinessRuleException, ResourceNotFoundException;

  /**
   * Listar produtos por categoria.
   *
   * @param categoria A categoria dos produtos a serem listados.
   * @return A lista com os produtos.
   * @throws BadRequestException Exceção da aplicação lançada pelo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   * @throws ResourceNotFoundException Exceção de recurso não encontrado lançada pelo método.
   */
  @Operation(summary = "Buscar produtos por categoria",
      description = Constantes.DESC_BUSCAR_POR_CAT)

  @Parameter(name = "categoria", description = "Categoria dos produtos que serão buscados",
      required = true)
  
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = Constantes.D200,
      content = @Content(mediaType = "application/json", examples = @ExampleObject(
        value = Constantes.E200))),

    @ApiResponse(responseCode = "204", description = Constantes.D204,
      content = @Content(mediaType = "application/json", examples = @ExampleObject(
        value = Constantes.E404))),

    @ApiResponse(responseCode = "400", description = Constantes.D400,
      content = @Content(mediaType = "application/json", examples = @ExampleObject(
        value = Constantes.E400))),

    @ApiResponse(responseCode = "422", description = Constantes.D422,
      content = @Content(mediaType = "application/json", examples = @ExampleObject(
        value = Constantes.E422))),

    @ApiResponse(responseCode = "500", description = Constantes.D500,
      content = @Content(mediaType = "application/json", examples = @ExampleObject(
        value = Constantes.E500))) })
  
  @GetMapping(value = "/buscar/{categoria}")

  ResponseEntity<List<ProdutoDto>>
      buscarProdutosPorCategoria(@PathVariable("categoria") String categoria)
      throws BadRequestException, BusinessRuleException, ResourceNotFoundException;

  /** 
   * Constantes usadas na APi de Produtos.
   */
  final class Constantes {

    private Constantes() {}

    public static final String DESC_CADASTRAR = "Para cadastrar um novo produto, "
        + "informe os dados do produto conforme o schema ProdutoDto no final desta página.<br>"
        + "A descrição do produto é opcional.";

    public static final String DESC_EDITAR = "Para editar um produto, informe o código "
        + "do produto (path) e os dados do produto (body) conforme o schema ProdutoDto ao "
        + "final desta página.";

    public static final String DESC_REMOVER = "Para remover um produto, informe o código do "
        + "produto a ser removido.";

    public static final String DESC_BUSCAR_POR_CAT = "Para buscar os produtos por categoria, "
        + "informe a categoria (lanche, acompanhamento, bebida ou sobremesa).";

    public static final String D200 = "Sucesso!";
    public static final String D201 = D200;
    public static final String D204 = D200;
    public static final String D400 = "Requisição inválida!";
    public static final String D404 = "Produto não encontrado!";
    public static final String D422 = "Operação não permitida!";
    public static final String D500 = "Erro!";

    public static final String E200 = "{ \"codigo\": 1,\"nome\": \"X-Monstrão\","
        + "\"descricao\": \"O lanche do marombeiro, repleto de whey e creatina.\","
        + "\"preco\": 35.9,\"categoria\": \"LANCHE\" }";

    public static final String E201 = E200;
    public static final String E204 = "";

    public static final String E400 = "{ \"timestamp\": \"2024-09-08T02:05:58.036+00:00\", "
        + "\"status\": 400, \"error\": \"Bad Request\", \"path\": \"/produtos/cadastrar\" }";

    public static final String E404 = "{ \"message\": \"Nenhum produto foi encontrado para "
        + "o código informado.\" }";

    public static final String E422 = "{ \"message\": \"O preço do produto deve ser maior "
        + "que 0.\" }";

    public static final String E500 = "{ \"message\": \"Ocorreu um erro inesperado no "
        + "servidor.\" }";

  }

}
