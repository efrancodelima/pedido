package br.com.fiap.soat.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração do Swagger.
 */
@Configuration
public class SwaggerConfig {

  /**
   * Retorna um objeto do tipo OpenAPI, que configura a exibição da API do swagger.
   *
   * @return Um objeto do tipo OpenAPI.
   */
  @Bean
  public OpenAPI customOpenApi() {

    var tagClientes = new Tag().name("Clientes")
        .description("Operações relacionadas a clientes");
    
    var tagPedidos = new Tag().name("Pedidos")
        .description("Operações relacionadas a pedidos");

    return new OpenAPI()

        .info(new Info()
            .title("Documentação da API")
            .version("2.0")
            .description("Documentação da API do microsservico de pedidos (visão do cliente)"
                + "<br>FIAP | Pós-tech | Software Architecture | Tech Challenge | Fase 4"))
            .addTagsItem(tagClientes)
            .addTagsItem(tagPedidos);
  }

}
