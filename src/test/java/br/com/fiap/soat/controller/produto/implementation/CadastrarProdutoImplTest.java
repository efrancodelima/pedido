package br.com.fiap.soat.controller.produto.implementation;

import br.com.fiap.soat.service.provider.produto.CadastrarProdutoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CadastrarProdutoImplTest {

  AutoCloseable closeable;

  @Mock
  CadastrarProdutoService serviceMock;

  @InjectMocks
  CadastrarProdutoImpl controller;
  
  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveCadastrarProdutoComSucesso() {}
}
