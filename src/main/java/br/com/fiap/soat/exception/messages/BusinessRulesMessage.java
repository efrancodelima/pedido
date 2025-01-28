package br.com.fiap.soat.exception.messages;

/**
 * Mensagens para exceções de regra de negócio.
 */
public enum BusinessRulesMessage {
    
  CLIENTE_JA_CADASTRADO("Já existe um cliente cadastrado com esse CPF."),
  CLI_EMAIL_MAX("O e-mail não pode ter mais de 40 caracteres."),
  CLI_NOME_EMAIL_NULL("Informe o nome ou o email do cliente."),
  CLI_NOME_INV("O nome do cliente deve conter, no mínimo, uma palavra com "
      + "três ou mais caracteres."),
  CLI_NOME_MAX("O nome do cliente não pode ter mais de 50 caracteres."),
  
  PROD_NOME_MIN("O nome do produto precisa ter, no mínimo, 5 caracteres."),
  PROD_NOME_MAX("O nome do produto não pode ter mais de 20 caracteres."),
  PROD_DESC_MIN("A descrição do produto precisa ter, no mínimo, 20 caracteres."),
  PROD_DESC_MAX("A descrição do produto não pode ter mais de 150 caracteres."),
  PROD_PRECO_MIN("O preço do produto deve ser maior que zero."),
  PROD_PRECO_MAX("O preço do produto não pode ser maior que R$ 300,00."),
  
  PED_ITEM_MIN("O pedido deve conter, pelo menos, um item.");

  private String mensagem;

  BusinessRulesMessage(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getMessage() {
    return mensagem;
  }
}
