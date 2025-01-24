package br.com.fiap.soat.apirest;

import br.com.fiap.soat.apirest.wrapper.ResponseWrapper;
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
public class CadastrarClienteApiImpl {

  private final CadastrarClienteService service;

  /**
   * O construtor público da classe.
   *
   * @param service O service para cadastrar o cliente.
   */
  @Autowired
  public CadastrarClienteApiImpl(CadastrarClienteService service) {
    this.service = service;
  }

  /**
   * Endpoint para o cadastro de clientes.
   *
   * @param clienteDto A requisição com os dados do cliente a ser cadastrado.
   * @return Um objeto do tipo ResponseEntity contendo o cliente cadastrado,
   *     em caso de sucesso, ou a mensagem de erro, em caso de falha.
   */
  @PostMapping(value = "/cadastrar")
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

