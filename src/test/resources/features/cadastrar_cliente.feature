# language: pt

Funcionalidade: Cadastrar cliente

  Cenário: Cadastro de cliente
    Dado que existe um cliente a ser cadastrado
    Quando o sistema receber a requisição do cliente a ser cadastrado
    Então deve retornar o cliente cadastrado com o código
