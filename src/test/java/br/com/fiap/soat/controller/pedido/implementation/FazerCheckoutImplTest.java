package br.com.fiap.soat.controller.pedido.implementation;

import br.com.fiap.soat.service.provider.pedido.FazerCheckoutService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class FazerCheckoutImplTest {

  AutoCloseable closeable;

  @Mock
  FazerCheckoutService serviceMock;

  @InjectMocks
  FazerCheckoutImpl controller;
  
  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveFazerCheckoutComSucesso() {}
  
}
