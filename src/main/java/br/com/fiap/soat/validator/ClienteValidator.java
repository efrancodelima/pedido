package br.com.fiap.soat.validator;

import br.com.fiap.soat.dto.ClienteDto;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages2.ClienteExceptions;
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
   * @param requisicao O objeto a ser validado.
   * @throws BadRequestException Exceção do tipo bad request lançada durante a validação.
   */
  public static void validar(ClienteDto requisicao) throws BadRequestException {
    
    verificarCamposObrigatorios(requisicao);
    
    CpfValidator.validar(requisicao.getCpf());
    validarNome(requisicao.getNome());
    validarEmail(requisicao.getEmail());
  }

  private static void verificarCamposObrigatorios(ClienteDto requisicao)
      throws BadRequestException {

    if (requisicao.getCpf() == null) {
      throw new BadRequestException("Informe o CPF do cliente.");
    }

    if (requisicao.getNome() == null && requisicao.getEmail() == null) {
      throw new BadRequestException("Informe o nome ou o email do cliente.");
    }
  }

  private static void validarNome(String nome) throws BadRequestException {

    if (nome != null) {

      if (nome.isEmpty()) {
        throw new BadRequestException("Informe o nome do cliente.");
      }
    
      if (nome.length() > 50) {
        throw new BadRequestException("O nome do cliente não pode ter mais de 50 caracteres.");
      }
        
      var palavras = getListaPalavras(nome, 3);
            
      if (palavras.isEmpty()) {
        throw new BadRequestException("O nome do cliente deve conter, no mínimo, "
            + "uma palavra com três ou mais caracteres.");
      }
    }
  }

  private static void validarEmail(String email) throws BadRequestException {

    if (email != null) {
      
      // Verifica se é string vazia
      if (email.isEmpty()) {
        throw new BadRequestException("O e-mail informado é inválido.");
      }
      
      // Verifica o limite de caracteres
      if (email.length() > 40) {
        throw new BadRequestException("O e-mail não pode ter mais de 40 caracteres.");
      
      }
      
      // Verifica o pattern
      String emailRegexRfc5322 = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
      Pattern pattern = Pattern.compile(emailRegexRfc5322);
        
      if (!pattern.matcher(email).matches()) {
        throw new BadRequestException(ClienteExceptions.EMAIL_INVALIDO.getMensagem());
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

  
}
