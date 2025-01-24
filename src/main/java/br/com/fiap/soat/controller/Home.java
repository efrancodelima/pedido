package br.com.fiap.soat.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para a página inicial.
 */
@RestController
@RequestMapping("/")
public class Home {

  /**
   * Mostra o link para a API web da aplicação.
   *
   * @return Uma string contendo o link da API.
   */
  @GetMapping
  @Hidden
  public String showHome() {
    return "Tech Challenge Fase 4: microsserviço de \"pedidos\" rodando!<br><br>"
        + "Link para a API: <a href=\"/swagger-ui/index.html\">Swagger UI</a>";
  }
}
