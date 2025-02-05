# language: pt

Funcionalidade: Cadastrar produto

  Cenário: Cadastro de produto
    Dado que existe um produto a ser cadastrado
    Quando o sistema receber a requisição de cadastro
    Então deve retornar o produto cadastrado com o código
