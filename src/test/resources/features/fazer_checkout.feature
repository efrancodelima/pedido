# language: pt

Funcionalidade: Fazer checkout

  Cenário: Cliente faz checkout do pedido
    Dado que o cliente possui um pedido
    Quando o pedido for enviado para checkout e a comunicação com os serviços externos falhar
    Então o sistema deve retornar o status bad gateway
