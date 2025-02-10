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

  @Bean
  public OpenAPI customOpenApi() {

    var tagClientes = new Tag().name("Cliente")
        .description("Operações relacionadas a clientes");
    
    var tagPedidos = new Tag().name("Pedido")
        .description("Operações relacionadas a pedidos");

    var tagProdutos = new Tag().name("Produto")
        .description("Operações relacionadas a produtos");

    return new OpenAPI()

        .info(new Info()
            .title("Documentação da API")
            .version("2.0")
            .description("Documentação da API do microsservico de PEDIDO"
                + "<br>FIAP | Pós-tech | Software Architecture | Tech Challenge | Fase 4"))
            .addTagsItem(tagClientes)
            .addTagsItem(tagProdutos)
            .addTagsItem(tagPedidos);
  }
}
