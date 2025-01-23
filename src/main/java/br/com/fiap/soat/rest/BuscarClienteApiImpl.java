package br.com.fiap.soat.rest;

import br.com.fiap.soat.dto.ClienteDto;
import br.com.fiap.soat.entity.ClienteJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.service.BuscarClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para buscar um cliente pelo CPF.
 */
@RestController
@RequestMapping("/clientes")
public class BuscarClienteApiImpl {

  private final BuscarClienteService service;

  /**
   * O construtor público da classe.
   *
   * @param service O service para buscar o cliente.
   */
  @Autowired
  public BuscarClienteApiImpl(BuscarClienteService service) {
    this.service = service;
  }

  /**
   * Endpopint para buscar o cliente pelo CPF.
   *
   * @param numeroCpf O número do CPF do cliente a ser buscado.
   * @return Um objeto do tipo ResponseEntity contendo o cliente encontrado,
   *     em caso de sucesso, ou a mensagem de erro, em caso de falha.
   */
  @GetMapping(value = "/buscar/{cpf}")
  public ResponseEntity<ResponseWrapper<ClienteJpa>>
      buscarCliente(@PathVariable("cpf") Long numeroCpf) {

    try {
      var cliente = service.execute(numeroCpf);
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(cliente));

    } catch (BadRequestException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ResponseWrapper<>(e.getMessage()));
    }
  }
    
}
