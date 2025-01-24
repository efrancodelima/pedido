package br.com.fiap.soat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Bean para a classe RestTemplate.
 */
@Configuration
public class AppConfig {

  /**
   * Retorna o bean.
   */
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
