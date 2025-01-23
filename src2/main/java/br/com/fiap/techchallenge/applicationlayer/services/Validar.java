package br.com.fiap.techchallenge.applicationlayer.services;

import br.com.fiap.techchallenge.applicationlayer.exceptions.ApplicationException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.ResourceNotFoundException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumNotFoundExceptions;
import java.util.List;

/**
 * Classe utilitária para auxiliar na validação das regras da aplicação.
 */
public final class Validar {

  private Validar() {}

  /**
   * Verifica se um objeto não é nulo e, em caso negativo,
   * lança uma exceção de regra de aplicação.
   *
   * @param objeto O objeto a ser verificado.
   * @param excecao O Enum com a mensagem da exceção a ser lançada.
   * @throws GatewayException Exceção de regra de aplicação lançada pelo método.
   */
  public static void notNull(Object objeto, EnumApplicationExceptions excecao)
      throws ApplicationException {

    if (objeto == null) {
      throw new ApplicationException(excecao.getMensagem());
    }
  }

  /**
   * Verifica se um objeto não é nulo e, em caso negativo,
   * lança uma exceção de recurso não encontrado.
   *
   * @param objeto O objeto a ser verificado.
   * @param excecao O Enum com a mensagem da exceção a ser lançada.
   * @throws ResourceNotFoundException Exceção de recurso não encontrado lançada pelo método.
   */
  public static void notNull(Object objeto, EnumNotFoundExceptions excecao)
      throws ResourceNotFoundException {

    if (objeto == null) {
      throw new ResourceNotFoundException(excecao.getMensagem());
    }
  }

  /**
   * Verifica se um número é maior que zero e, em caso negativo,
   * lança uma exceção de regra de aplicação.
   *
   * @param numero O número a ser verificado.
   * @param excecao O Enum com a mensagem da exceção a ser lançada.
   * @throws GatewayException Exceção de regra de aplicação lançada pelo método.
   */
  public static void maiorQueZero(long numero,
      EnumApplicationExceptions excecao) throws ApplicationException {

    if (numero <= 0) {
      throw new ApplicationException(excecao.getMensagem());
    }
  }

  /**
   * Verifica se uma lista não é vazia e, em caso negativo,
   * lança uma exceção de recurso não encontrado.
   *
   * @param lista A lista a ser verificada.
   * @param excecao O Enum com a mensagem da exceção a ser lançada.
   * @throws GatewayException Exceção de recurso não encontrado lançada pelo método.
   */
  public static <T> void listNotEmpty(List<T> lista, EnumNotFoundExceptions excecao)
      throws ResourceNotFoundException {

    if (lista.isEmpty()) {
      throw new ResourceNotFoundException(excecao.getMensagem());
    }
  }
}
