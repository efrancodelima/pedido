package br.com.fiap.soat.mapper.cliente;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import br.com.fiap.soat.dto.controller.request.ClienteDto;
import org.junit.jupiter.api.Test;

class ClienteMapperTest {

  @Test
  void deveMapearComSucesso() {
    var cliente = new ClienteDto(11122233396L, "Nome do cliente", "email@email.com");
    assertDoesNotThrow(() -> {
      ClienteMapper.toEntity(cliente);
    });
  }
}
