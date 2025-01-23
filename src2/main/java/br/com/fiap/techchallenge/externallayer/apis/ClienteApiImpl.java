package br.com.fiap.techchallenge.externallayer.apis;

import br.com.fiap.techchallenge.applicationlayer.exceptions.ApplicationException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.ResourceNotFoundException;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.externallayer.apis.interfaces.ClienteApi;
import br.com.fiap.techchallenge.interfacelayer.controllers.ClienteControllerImpl;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.ClienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para a APi clientes.
 */
@RestController
@RequestMapping("/clientes")
public class ClienteApiImpl implements ClienteApi {

  private final ClienteControllerImpl controller;

  /**
   * O construtor público da classe.
   *
   * @param controller O controller de Cliente.
   */
  @Autowired
  public ClienteApiImpl(ClienteControllerImpl controller) {
    this.controller = controller;
  }

  @Override
  public ResponseEntity<Cliente> cadastrarCliente(ClienteDto clienteDto)
      throws ApplicationException, BusinessRuleException {

    return controller.cadastrarCliente(clienteDto);
  }

  @Override
  public ResponseEntity<Cliente> buscarClientePorCpf(long cpf)
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException {

    return controller.buscarClientePeloCpf(cpf);
  }

}
