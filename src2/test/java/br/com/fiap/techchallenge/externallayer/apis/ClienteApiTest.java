package br.com.fiap.techchallenge.externallayer.apis;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.ClienteDto;
import br.com.fiap.techchallenge.interfacelayer.gateways.repositories.InClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

/**
 * Testes de integração a partir dos endpoints da API clientes.
 */
@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class ClienteApiTest {
  
  @Autowired
  InClienteRepository repo;

  @Autowired
  ClienteApiImpl api;

  @Test
  void verificaSeTabelaFoiPrePopulada() {
    var numeroEntidades = repo.count();
    assertEquals(true, numeroEntidades > 0);
  }

  @Test
  void deveCadastrarClienteComSucesso() {

    var clienteDto = new ClienteDto(11122233396L, "Nome do cliente", "email@email.com");

    assertDoesNotThrow(() -> {

      var response = api.cadastrarCliente(clienteDto);
      assertEquals(HttpStatus.CREATED, response.getStatusCode());
    });
  }

  @Test
  void deveBuscarClientePorCpf() {

    assertDoesNotThrow(() -> {
      
      var response = api.buscarClientePorCpf(23456789092L);
      assertEquals(HttpStatus.OK, response.getStatusCode());
    });
  }
  
}
