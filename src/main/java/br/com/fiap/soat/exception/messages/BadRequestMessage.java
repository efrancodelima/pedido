package br.com.fiap.soat.exception.messages;

/**
 * Mensagens para exceções do tipo Bad Request.
 */
public enum BadRequestMessage {
    
  CLI_NULL("Informe os dados do cliente."),
  CLI_COD_MIN("O código do cliente deve ser igual ou maior que 1."),
  CLI_CPF_INV("O CPF informado é inválido."),
  CLI_CPF_NULL("Informe o CPF do cliente."),
  CLI_EMAIL_INV("O e-mail informado é inválido."),
  
  PROD_NULL("Informe os dados do produto."),
  PROD_COD_NULL("Informe o código do produto."),
  PROD_COD_MIN("O código do produto deve ser igual ou maior que 1."),
  PROD_NOME_NULL("Informe o nome do produto."),
  PROD_PRECO_NULL("Informe o preço do produto."),
  PROD_CAT_NULL("Informe a categoria do produto."),
  PROD_CAT_INV("A categoria do produto é inválida."),

  PED_COD_NULL("Informe o código do produto."),
  PED_COD_MIN("O código do produto deve ser igual ou maior que 1."),
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
