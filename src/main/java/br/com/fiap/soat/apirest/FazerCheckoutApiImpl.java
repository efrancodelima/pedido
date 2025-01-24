package br.com.fiap.soat.apirest;

import br.com.fiap.soat.apirest.wrapper.ResponseWrapper;
import br.com.fiap.soat.dto.PedidoDto;
import br.com.fiap.soat.dto.StatusPedidoDto;
import br.com.fiap.soat.exception.BadGatewayException;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.service.provider.FazerCheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para fazer checkout do pedido.
 */
@RestController
@RequestMapping("/pedidos")
public class FazerCheckoutApiImpl {

  // Atributos
  private final FazerCheckoutService service;

  /**
   * O construtor público da classe.
   *
   * @param service O service para fazer checkout do pedido.
   */
  @Autowired
  public FazerCheckoutApiImpl(FazerCheckoutService service) {
    this.service = service;
  }

  /**
   * Endpoint para o checkout de pedidos.
   *
   * @param pedidoDto O pedido para o checkout.
   * @return Um objeto do tipo ResponseEntity contendo o status do pedido,
   *     em caso de sucesso, ou a mensagem de erro, em caso de falha.
   */
  @PostMapping(value = "/checkout")
  public ResponseEntity<ResponseWrapper<StatusPedidoDto>>
      fazerCheckout(@RequestBody PedidoDto pedidoDto) {

    try {
      var statusPedidoDto = service.execute(pedidoDto);
      return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>(statusPedidoDto));

    } catch (BadGatewayException e) {
      return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
          .body(new ResponseWrapper<>(e.getMessage()));
    
    } catch (BadRequestException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ResponseWrapper<>(e.getMessage()));
    
    } catch (NotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new ResponseWrapper<>(e.getMessage()));
    }
  }



  // var StatusPedidoDto = 
    
}
