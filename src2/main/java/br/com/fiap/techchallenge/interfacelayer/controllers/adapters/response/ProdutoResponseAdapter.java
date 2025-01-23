package br.com.fiap.techchallenge.interfacelayer.controllers.adapters.response;

import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Classe ProdutoResponseAdapter.
 */
public final class ProdutoResponseAdapter {

  private ProdutoResponseAdapter() {}

  /**
   * Adapta um objeto do tipo Produto para o tipo ResponseEntity-Produto.
   *
   * @param produto O objeto a ser adaptado.
   * @param httpStatus O status HTTP do ResponseEntity.
   * @return O objeto adaptado.
   */
  public static ResponseEntity<Produto> adaptar(Produto produto, HttpStatus httpStatus) {
    return new ResponseEntity<>(produto, httpStatus);
  }

  /**
   * Adapta um objeto do tipo List-Produto para o tipo ResponseEntity-List-Produto.
   *
   * @param produtos O objeto a ser adaptado.
   * @param httpStatus O status HTTP do ResponseEntity.
   * @return O objeto adaptado.
   */
  public static ResponseEntity<List<Produto>> 
      adaptar(List<Produto> produtos, HttpStatus httpStatus) {
        
    return new ResponseEntity<>(produtos, httpStatus);
  }

}
