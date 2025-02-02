package br.com.fiap.soat.service.provider.produto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

import br.com.fiap.soat.entity.CategoriaProduto;
import br.com.fiap.soat.entity.ProdutoJpa;
import br.com.fiap.soat.repository.ProdutoRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class BuscarProdutosServiceTest {
  
  AutoCloseable closeable;

  @Mock
  ProdutoRepository repositoryMock;

  @InjectMocks
  BuscarProdutosService service;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveBuscarProdutoComSucesso() throws Exception {

    // Arrange
    var produto = new ProdutoJpa(1L, "Nome do produto", null,
        BigDecimal.valueOf(12.9), CategoriaProduto.BEBIDA);

    doReturn(Optional.of(produto)).when(repositoryMock).findById(anyLong());

    // Act
    var response = service.execute(getCodigos());

    // Assert
    assertEquals(1, response.size());
    assertEquals(produto, response.get(0));
  }

  private List<Long> getCodigos() {
    var lista = new ArrayList<Long>();
    lista.add(1L);
    return lista;
  }
}
