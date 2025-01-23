package br.com.fiap.techchallenge.interfacelayer.gateways.mappers;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cpf;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.ClienteJpa;
import org.junit.jupiter.api.Test;

/**
 * Classe de testes para ClienteMapper.
 */
class ClienteMapperTest {

  @Test
  void deveMapearClienteParaClienteJpa() throws BusinessRuleException {
  
    var cpf = new Cpf(11122233396L);
    var cliente = new Cliente(1L, cpf, "Nome do cliente", "email@email.com");

    assertDoesNotThrow(() -> {

      var clienteJpa = ClienteMapper.getClienteJpa(cliente);

      assertEquals(cliente.getCodigo(), clienteJpa.getCodigo());
      assertEquals(cliente.getCpf().pegarNumeroComDigito(), (long) clienteJpa.getCpf());
      assertEquals(cliente.getNome(), clienteJpa.getNome());
      assertEquals(cliente.getEmail(), clienteJpa.getEmail());
    });
  }

  @Test
  void deveMapearClienteJpaParaCliente() {

    var clienteJpa = new ClienteJpa(1L, 11122233396L, "Nome do cliente", "email@email.com");

    assertDoesNotThrow(() -> {

      var cliente = ClienteMapper.getCliente(clienteJpa);

      assertEquals(clienteJpa.getCodigo(), cliente.getCodigo());
      assertEquals((long) clienteJpa.getCpf(), cliente.getCpf().pegarNumeroComDigito());
      assertEquals(clienteJpa.getNome(), cliente.getNome());
      assertEquals(clienteJpa.getEmail(), cliente.getEmail());
    });
  }
  
}
