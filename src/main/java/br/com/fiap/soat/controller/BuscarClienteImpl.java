package br.com.fiap.soat.controller;

import br.com.fiap.soat.controller.contract.BuscarCliente;
import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.entity.ClienteJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.service.provider.BuscarClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para buscar um cliente pelo CPF.
 */
@RestController
@RequestMapping("/clientes")
public class BuscarClienteImpl implements BuscarCliente {

  private final BuscarClienteService service;

  /**
   * O construtor público da classe.
   *
   * @param service O service para buscar o cliente.
   */
  @Autowired
  public BuscarClienteImpl(BuscarClienteService service) {
    this.service = service;
  }

  @Override
  public ResponseEntity<ResponseWrapper<ClienteJpa>>
      buscarClientePorCpf(@PathVariable("cpf") long numeroCpf) {

    try {
      var cliente = service.execute(numeroCpf);
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(cliente));

    } catch (BadRequestException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ResponseWrapper<>(e.getMessage()));
    
    } catch (NotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new ResponseWrapper<>(e.getMessage()));
    }
  }
    
}
