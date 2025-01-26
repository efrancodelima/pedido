package br.com.fiap.soat.exception.messages;

/**
 * Mensagens para exceções do tipo Bad Request.
 */
public enum BadRequestMessage {
    
  CLI_COD_MIN("O código do cliente deve ser igual ou maior que 1."),
  CLI_CPF_INV("O CPF informado é inválido."),
  CLI_CPF_NULL("Informe o CPF do cliente."),
  CLI_EMAIL_INV("O e-mail informado é inválido."),
  CLI_EMAIL_MAX("O e-mail não pode ter mais de 40 caracteres."),
  CLI_NOME_EMAIL_NULL("Informe o nome ou o email do cliente."),
  CLI_NOME_INV("O nome do cliente deve conter, no mínimo, uma palavra com "
      + "três ou mais caracteres."),
  CLI_NOME_MAX("O nome do cliente não pode ter mais de 50 caracteres."),
  
  PROD_NULL("Informe os dados do produto."),
  PROD_COD_NULL("Informe o código do produto."),
  PROD_COD_MIN("O código do produto deve ser igual ou maior que 1."),
  PROD_NOME_NULL("Informe o nome do produto."),
  PROD_NOME_MIN("O nome do produto precisa ter, no mínimo, 5 caracteres."),
  PROD_NOME_MAX("O nome do produto não pode ter mais de 20 caracteres."),
  PROD_DESC_MIN("A descrição do produto precisa ter, no mínimo, 20 caracteres."),
  PROD_DESC_MAX("A descrição do produto não pode ter mais de 150 caracteres."),
  PROD_PRECO_NULL("Informe o preço do produto."),
  PROD_PRECO_MIN("O preço do produto deve ser maior que zero."),
  PROD_PRECO_MAX("O preço do produto não pode ser maior que R$ 300,00."),
  PROD_CAT_NULL("Informe a categoria do produto."),
  PROD_CAT_INV("A categoria do produto é inválida."),

  PED_COD_NULL("Informe o código do produto."),
  PED_COD_MIN("O código do produto deve ser igual ou maior que 1."),
  PED_ITEM_MIN("O pedido deve conter, pelo menos, um item."),
  PED_ITEM_QTDE_NULL("Informe a quantidade do item."),
  PED_ITEM_QTDE_MIN("A quantidade do item deve ser igual ou maior que 1."),

  LIST_COD_EMPTY("Informe, pelo menos, um código para realizar a busca.");

  private String mensagem;

  BadRequestMessage(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getMessage() {
    return mensagem;
  }

}
