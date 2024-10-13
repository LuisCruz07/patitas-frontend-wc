package pe.edu.cibertec.patitasfrontendwc.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

//clase 05b-2:23:45
@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClientAutenticacion(WebClient.Builder builder) {
        //configuracion de timeout en HttpClient Netty
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)//timeout de conexion en milisegundos
                .responseTimeout(Duration.ofSeconds(10))//timeout de lectura de respuesta
                .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(10, TimeUnit.SECONDS)));//se le llama timeout de lectura de cada paquete

        return builder
             .baseUrl("http://localhost:8081/autenticacion")
                //implementar timeout
                .clientConnector(new ReactorClientHttpConnector(httpClient))//implementar timeout
                .build();
    }

}
