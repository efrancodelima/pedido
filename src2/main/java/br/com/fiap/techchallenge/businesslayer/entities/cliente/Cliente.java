package br.com.fiap.techchallenge.businesslayer.entities.cliente;

import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.businesslayer.exceptions.messages.ClienteExceptions;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Classe Cliente.
 */
public class Cliente {
  private Long codigo;
  private Cpf cpf;
  private String nome;
  private String email;

  /**
   * Construtor público de cliente.
   *
   * @param codigo O código do cliente.
   * @param cpf O Cpf do cliente.
   * @param nome O nome do cliente.
   * @param email O e-mail do cliente.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo construtor.
   */
  public Cliente(Long codigo, Cpf cpf, String nome, String email) throws BusinessRuleException {
    setCodigo(codigo);
    setCpf(cpf);
    setNome(nome);
    setEmail(email);
    validarCliente(nome, email);
  }

  // Getters
  public Long getCodigo() {
    return codigo;
  }

  public Cpf getCpf() {
    return cpf;
  }

  public String getNome() {
    return nome;
  }

  public String getEmail() {
    return email;
  }

  // Setters
  private void setCodigo(Long codigo) throws BusinessRuleException {
    validarCodigo(codigo);
    this.codigo = codigo;
  }

  private void setCpf(Cpf cpf) throws BusinessRuleException {
    validarCpf(cpf);
    this.cpf = cpf;
  }

  private void setNome(String nome) throws BusinessRuleException {
    nome = nome == null ? null : nome.trim();
    validarNome(nome);
    this.nome = nome;
  }

  private void setEmail(String email) throws BusinessRuleException {
    email = email == null ? null : email.trim();
    validarEmail(email);
    this.email = email;
  }

  // Métodos de validação
  private void validarCodigo(Long codigo) throws BusinessRuleException {
    if (codigo != null && codigo < 1) {
      throw new BusinessRuleException(ClienteExceptions.CODIGO_MIN.getMensagem());
    }
  }

  private void validarCpf(Cpf cpf) throws BusinessRuleException {
    if (cpf == null) {
      throw new BusinessRuleException(ClienteExceptions.CPF_NULO.getMensagem());
    }
  }

  private void validarNome(String nome) throws BusinessRuleException {
    if (nome != null) {

      if (nome.isEmpty()) {
        throw new BusinessRuleException(ClienteExceptions.NOME_INVALIDO.getMensagem());

      } else if (nome.length() > 50) {
        throw new BusinessRuleException(ClienteExceptions.NOME_MAX_CHAR.getMensagem());

      } else {
        ArrayList<String> palavras = getListaPalavras(nome, 3);
        if (palavras.isEmpty()) {
          throw new BusinessRuleException(ClienteExceptions.NOME_INVALIDO.getMensagem());
        }
      }  
    }
  }

  private void validarEmail(String email) throws BusinessRuleException {
    if (email != null) {
      
      if (email.isEmpty()) {
        throw new BusinessRuleException(ClienteExceptions.EMAIL_INVALIDO.getMensagem());

      } else if (email.length() > 40) {
        throw new BusinessRuleException(ClienteExceptions.EMAIL_MAX_CHAR.getMensagem());
      
      } else {
        String emailRegexRfc5322 = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegexRfc5322);
        
        if (!pattern.matcher(email).matches()) {
          throw new BusinessRuleException(ClienteExceptions.EMAIL_INVALIDO.getMensagem());
        }
      }
    } 
  }

  private void validarCliente(String nome, String email) throws BusinessRuleException {
    if (nome == null && email == null) {
      throw new BusinessRuleException(ClienteExceptions.NOME_EMAIL_NULOS.getMensagem());
    }
  }

  // Métodos auxiliares
  private ArrayList<String> getListaPalavras(String texto, int minChar) {
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