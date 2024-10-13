package pe.edu.cibertec.patitasfrontendwc.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/*PARTE 2 NUM 1*/
@Configuration
public class RestTemplateConfig {
@Bean
    public RestTemplate restTemplateAutenticacion(RestTemplateBuilder builder) {

    return builder
            .rootUri("http://localhost:8081/autenticacion")
            .setConnectTimeout(Duration.ofSeconds(10)) //tiempo de establecimiento de conexion
            .setReadTimeout(Duration.ofSeconds(10))// timeoout de lectura de la respuesta
            .build();
    }
}
