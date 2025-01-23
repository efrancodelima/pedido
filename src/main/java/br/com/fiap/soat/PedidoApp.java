package br.com.fiap.soat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

/**
 * Classe responsável por iniciar o microsserviço.
 */
@SpringBootApplication
public class PedidoApp {

  /**
   * Método responsável por iniciar o microsserviço.
   */
  public static void main(String[] args) {
    var application = new SpringApplication(PedidoApp.class);
    application.setApplicationStartup(new BufferingApplicationStartup(1024));
    application.run(args);
  }  
}
