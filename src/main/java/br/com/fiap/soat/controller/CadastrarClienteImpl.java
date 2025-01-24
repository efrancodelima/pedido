package br.com.fiap.soat.controller;

import br.com.fiap.soat.controller.contract.CadastrarCliente;
import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.dto.ClienteDto;
import br.com.fiap.soat.entity.ClienteJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.service.provider.CadastrarClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para cadastro de clientes.
 */
@RestController
@RequestMapping("/clientes")
public class CadastrarClienteImpl implements CadastrarCliente {

  private final CadastrarClienteService service;

  /**
   * O construtor público da classe.
   *
   * @param service O service para cadastrar o cliente.
   */
  @Autowired
  public CadastrarClienteImpl(CadastrarClienteService service) {
    this.service = service;
  }

  @Override
  public ResponseEntity<ResponseWrapper<ClienteJpa>>
      cadastrarCliente(@RequestBody ClienteDto clienteDto) {

    try {
      var cliente = service.execute(clienteDto);
      return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>(cliente));

    } catch (BadRequestException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ResponseWrapper<>(e.getMessage()));
    }
  }

}

