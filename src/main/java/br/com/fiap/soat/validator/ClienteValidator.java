package br.com.fiap.soat.validator;

import br.com.fiap.soat.dto.ClienteDto;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Responsável por validar um objeto ClienteDto recebido na requisição ao microsserviço.
 */
public class ClienteValidator {

  private ClienteValidator() {}

  /**
   * Valida um objeto do tipo ClienteDto.
   *
   * @param clienteDto O objeto a ser validado.
   * @throws BadRequestException Exceção do tipo bad request lançada durante a validação.
   */
  public static void validar(ClienteDto clienteDto) throws BadRequestException {
    
    if (isNullOrEmpty(clienteDto.getNome()) && isNullOrEmpty(clienteDto.getEmail())) {
      throw new BadRequestException(BadRequestMessage.NOME_EMAIL_NULL);
    }
    
    CpfValidator.validar(clienteDto.getCpf());
    validarNome(clienteDto.getNome());
    validarEmail(clienteDto.getEmail());
  }

  private static void validarNome(String nome) throws BadRequestException {

    if (!isNullOrEmpty(nome)) {

      // Verifica o limite de caracteres
      if (nome.length() > 50) {
        throw new BadRequestException(BadRequestMessage.NOME_MAX);
      }
      
      // Verifica as palavras
      var palavras = getListaPalavras(nome, 3);
            
      if (palavras.isEmpty()) {
        throw new BadRequestException(BadRequestMessage.NOME_INVALIDO);
      }
    }
  }

  private static void validarEmail(String email) throws BadRequestException {

    if (!isNullOrEmpty(email)) {
      
      // Verifica o limite de caracteres
      if (email.length() > 40) {
        throw new BadRequestException(BadRequestMessage.EMAIL_MAX);
      }
      
      // Verifica o pattern
      String emailRegexRfc5322 = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
      Pattern pattern = Pattern.compile(emailRegexRfc5322);
        
      if (!pattern.matcher(email).matches()) {
        throw new BadRequestException(BadRequestMessage.EMAIL_INVALIDO);
      }
    } 
  }
  
  private static ArrayList<String> getListaPalavras(String texto, int minChar) {

    String[] palavras = texto.split(" ");
    ArrayList<String> palavrasValidas = new ArrayList<>();

    for (String palavra : palavras) {
      if (palavra.length() >= minChar) {
        palavrasValidas.add(palavra);
      }
    }
    
    return palavrasValidas;
  }

  private static boolean isNullOrEmpty(String str) {
    return str == null || str.trim().isEmpty();
  }

}
