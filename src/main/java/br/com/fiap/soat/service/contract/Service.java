package br.com.fiap.soat.service.contract;

/**
 * Interface para os services.
 */
public interface Service<P, Q> {

  /**
   * Executa a ação pela qual o service é responsável.
   */
  Q execute(P requisicao) throws Exception;
}

