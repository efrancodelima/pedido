package br.com.fiap.techchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

/**
 * Classe responsável por iniciar a aplicação.
 */
@SpringBootApplication
public class TechChallengeApplication {

  /**
   * Método responsável por iniciar a aplicação.
   */
  public static void main(String[] args) {
    var application = new SpringApplication(TechChallengeApplication.class);
    application.setApplicationStartup(new BufferingApplicationStartup(1024));
    application.run(args);
  }

}
