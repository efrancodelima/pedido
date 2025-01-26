package br.com.fiap.soat.exception.messages;

/**
 * Mensagens para exceções do tipo Bad Request.
 */
public enum BadRequestMessage {
    
  CODIGO_CLIENTE("O código do cliente deve ser igual ou maior que 1."),
  CODIGO_PRODUTO("O código do produto deve ser igual ou maior que 1"),
  CPF_INVALIDO("O CPF informado é inválido."),
  CPF_NULL("Informe o CPF do cliente."),
  EMAIL_INVALIDO("O e-mail informado é inválido."),
  EMAIL_MAX("O e-mail não pode ter mais de 40 caracteres."),
  ITEM_MIN("O pedido deve conter, pelo menos, um item."),
  NOME_EMAIL_NULL("Informe o nome ou o email do cliente."),
  NOME_INVALIDO("O nome do cliente deve conter, no mínimo, uma palavra com "
      + "três ou mais caracteres."),
  NOME_MAX("O nome do cliente não pode ter mais de 50 caracteres."),
  QTDE_ITEM("A quantidade do item deve ser igual ou maior que 1"),
  
  PROD_NULO("Informe os dados do produto."),
  PROD_COD_NULO("Informe o código do produto."),
  PROD_COD_MIN("O código do produto deve ser igual ou maior que 1."),
  PROD_NOME_NULO("Informe o nome do produto."),
  PROD_NOME_MIN("O nome do produto precisa ter, no mínimo, 5 caracteres."),
  PROD_NOME_MAX("O nome do produto não pode ter mais de 20 caracteres."),
  PROD_DESC_MIN("A descrição do produto precisa ter, no mínimo, 20 caracteres."),
  PROD_DESC_MAX("A descrição do produto não pode ter mais de 150 caracteres."),
  PROD_PRECO_NULO("Informe o preço do produto."),
  PROD_PRECO_MIN("O preço do produto deve ser maior que zero."),
  PROD_PRECO_MAX("O preço do produto não pode ser maior que R$ 300,00."),
  PROD_CAT_NULA("Informe a categoria do produto."),

  LIST_COD_EMPTY("Informe, pelo menos, um código para realizar a busca."),

  CAT_NULA("Informe a categoria do produto."),
  CAT_INV("A categoria do produto é inválida.");


  private String mensagem;

  BadRequestMessage(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getMessage() {
    return mensagem;
  }

}
