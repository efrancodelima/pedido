package br.com.fiap.techchallenge.externallayer.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        var tagClientes = new Tag().name("Clientes").description("Operações relacionadas a clientes");
        var tagProdutos = new Tag().name("Produtos").description("Operações relacionadas a produtos");
        var tagPedidos = new Tag().name("Pedidos").description("Operações relacionadas a pedidos");

        return new OpenAPI()
                .info(new Info()
                        .title("Documentação da API")
                        .version("2.0")
                        .description("Documentação da API do Tech Challenge Fase 2"
                                + "<br>FIAP | Pós-tech | Software Architecture"))
                .addTagsItem(tagClientes)
                .addTagsItem(tagProdutos)
                .addTagsItem(tagPedidos);
    }

}
