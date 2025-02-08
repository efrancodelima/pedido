# language: pt

Funcionalidade: Consultar pedido

  Cenário: Usuário deseja consultar o conteúdo de um pedido
    Dado que o cliente fez um pedido
    Quando o usuário consultar o pedido
    Então o sistema deve retornar o pedido feito
