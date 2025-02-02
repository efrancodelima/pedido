package br.com.fiap.soat.service.consumer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.dto.service.request.CriarPagamentoDto;
import br.com.fiap.soat.dto.service.response.PagamentoDto;
import br.com.fiap.soat.exception.BadGatewayException;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

class NotificarPagamentoServiceTest {

  AutoCloseable closeable;
  ParameterizedTypeReference<ResponseWrapper<PagamentoDto>> conversao;

  @Mock
  private RestTemplate restTemplate;

  @InjectMocks
  NotificarPagamentoService service;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
    conversao = new ParameterizedTypeReference<ResponseWrapper<PagamentoDto>>() {};
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveNotificarComSucesso() {

    // Arrange
    var criarPagamento = new CriarPagamentoDto(1L, BigDecimal.valueOf(28));
    var pagamento = new PagamentoDto(1L, 1L, BigDecimal.valueOf(28), "", "");
    var response = ResponseEntity.ok(new ResponseWrapper<>(pagamento));

    doReturn(response).when(restTemplate)
      .exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(conversao));

    // Act and assert
    assertDoesNotThrow(() -> {
      service.execute(criarPagamento);
    });
  }

  @Test
  void deveEncapsularExcecaoRecebida() throws Exception {

    // Arrange
    var criarPagamento = new CriarPagamentoDto(1L, BigDecimal.valueOf(28));
    var msgExcecao = "Uma mensagem qualquer.";

    doThrow(new RuntimeException(msgExcecao)).when(restTemplate)
      .exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(conversao));

    // Act and assert
    var excecao = assertThrows(BadGatewayException.class, () -> {
      service.execute(criarPagamento);
    });
    assertEquals(msgExcecao, excecao.getMessage());
  }
}
