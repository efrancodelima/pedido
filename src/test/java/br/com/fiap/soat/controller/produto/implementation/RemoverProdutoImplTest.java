package br.com.fiap.soat.controller.produto.implementation;

import br.com.fiap.soat.service.provider.produto.RemoverProdutoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class RemoverProdutoImplTest {

  AutoCloseable closeable;

  @Mock
  RemoverProdutoService serviceMock;

  @InjectMocks
  RemoverProdutoImpl controller;
  
  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveRemoverProdutoComSucesso() {}
}
