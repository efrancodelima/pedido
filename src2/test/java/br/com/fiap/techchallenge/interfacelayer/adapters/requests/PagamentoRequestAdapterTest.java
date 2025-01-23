package br.com.fiap.techchallenge.interfacelayer.adapters.requests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamentoEnum;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.businesslayer.exceptions.messages.StatusPagamentoExceptions;
import br.com.fiap.techchallenge.interfacelayer.controllers.adapters.request.PagamentoRequestAdapter;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.mercadopago.PagamentoDto;
import org.junit.jupiter.api.Test;

/**
 * Classe de testes para PagamentoRequestAdapter.
 */
class PagamentoRequestAdapterTest {

  @Test
  void deveAdaptarPagamentoDtoParaStatusPagamento() {

    var pagPending = new PagamentoDto(1L, "PENDING");
    var pagInProcess = new PagamentoDto(1L, "IN_PROCESS");
    var pagApproved = new PagamentoDto(1L, "APPROVED");
    var pagRejected = new PagamentoDto(1L, "REJECTED");
    var pagInvalid = new PagamentoDto(1L, "XYZ");

    assertDoesNotThrow(() -> {

      var statusPagamento = PagamentoRequestAdapter.adaptar(pagPending);
      assertEquals(pagPending.getId(), statusPagamento.getCodigo());
      assertEquals(
          StatusPagamentoEnum.AGUARDANDO_PAGAMENTO,
          statusPagamento.getStatus());

      statusPagamento = PagamentoRequestAdapter.adaptar(pagInProcess);
      assertEquals(pagInProcess.getId(), statusPagamento.getCodigo());
      assertEquals(
          StatusPagamentoEnum.AGUARDANDO_PAGAMENTO,
          statusPagamento.getStatus());

      statusPagamento = PagamentoRequestAdapter.adaptar(pagApproved);
      assertEquals(pagApproved.getId(), statusPagamento.getCodigo());
      assertEquals(
          StatusPagamentoEnum.APROVADO,
          statusPagamento.getStatus());

      statusPagamento = PagamentoRequestAdapter.adaptar(pagRejected);
      assertEquals(pagRejected.getId(), statusPagamento.getCodigo());
      assertEquals(
          StatusPagamentoEnum.REPROVADO,
          statusPagamento.getStatus());
    });

    var exception = assertThrows(BusinessRuleException.class, () -> {
      PagamentoRequestAdapter.adaptar(pagInvalid);
    });

    assertEquals(StatusPagamentoExceptions.STATUS_NULO.getMensagem(), exception.getMessage());
  }

}
