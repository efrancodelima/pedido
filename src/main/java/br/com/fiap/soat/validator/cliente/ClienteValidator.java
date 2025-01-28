package br.com.fiap.soat.validator.cliente;

import br.com.fiap.soat.dto.controller.ClienteDto;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.BusinessRulesException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import br.com.fiap.soat.exception.messages.BusinessRulesMessage;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Responsável por validar um objeto ClienteDto recebido na requisição ao microsserviço.
 */
public class ClienteValidator {

  private ClienteValidator() {}

  public static void validar(ClienteDto clienteDto)
      throws BadRequestException, BusinessRulesException {
    
    if (isNullOrEmpty(clienteDto.getNome()) && isNullOrEmpty(clienteDto.getEmail())) {
      throw new BusinessRulesException(BusinessRulesMessage.CLI_NOME_EMAIL_NULL);
    }
    
    CpfValidator.validar(clienteDto.getCpf());
    validarNome(clienteDto.getNome());
    validarEmail(clienteDto.getEmail());
  }

  private static void validarNome(String nome) throws BusinessRulesException {

    if (!isNullOrEmpty(nome)) {

      // Verifica o limite de caracteres
      if (nome.length() > 50) {
        throw new BusinessRulesException(BusinessRulesMessage.CLI_NOME_MAX);
      }
      
      // Verifica as palavras
      var palavras = getListaPalavras(nome, 3);
            
      if (palavras.isEmpty()) {
        throw new BusinessRulesException(BusinessRulesMessage.CLI_NOME_INV);
      }
    }
  }

  private static void validarEmail(String email)
        throws BadRequestException, BusinessRulesException {

    if (!isNullOrEmpty(email)) {
      
      // Verifica o limite de caracteres
      if (email.length() > 40) {
        throw new BusinessRulesException(BusinessRulesMessage.CLI_EMAIL_MAX);
      }
      
      // Verifica o pattern
      String emailRegexRfc5322 = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
      Pattern pattern = Pattern.compile(emailRegexRfc5322);
        
      if (!pattern.matcher(email).matches()) {
        throw new BadRequestException(BadRequestMessage.CLI_EMAIL_INV);
      }
    } 
  }
  
  private static ArrayList<String> getListaPalavras(String texto, int minChar) {

    String[] palavras = texto.split(" ");
    var palavrasValidas = new ArrayList<String>();

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
