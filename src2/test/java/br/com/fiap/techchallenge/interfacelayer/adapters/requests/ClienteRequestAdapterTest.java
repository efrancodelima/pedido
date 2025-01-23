package br.com.fiap.techchallenge.interfacelayer.adapters.requests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import br.com.fiap.techchallenge.interfacelayer.controllers.adapters.request.ClienteRequestAdapter;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.ClienteDto;
import org.junit.jupiter.api.Test;

/**
 * Classe de testes para ClienteRequestAdapter.
 */
class ClienteRequestAdapterTest {
  
  @Test
  void deveAdaptarClienteDtoParaCliente() {
    
    var cpf = 11122233396L;
    var clienteDto = new ClienteDto(cpf, "Arthur Conan Doyle", "conanad@gmail.com");
    
    assertDoesNotThrow(() -> {
      var cliente = ClienteRequestAdapter.adaptar(clienteDto);
      assertEquals((long) clienteDto.getCpf(), cliente.getCpf().pegarNumeroComDigito());
    });

  }
}
