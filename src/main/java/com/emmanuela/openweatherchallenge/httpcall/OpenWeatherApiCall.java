package com.emmanuela.openweatherchallenge.httpcall;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


@Component
@Slf4j
public class OpenWeatherApiCall {

    @Value("${application.http.callout.api.openweather.baseurl.apikeys}")
    private String baseUrl;
    private String modifiedWeatherUrl;

    public String openWeatherApi(String city){

        try{

            String encodeUrl = URLEncoder.encode(city, StandardCharsets.UTF_8.name());
            modifiedWeatherUrl = baseUrl + encodeUrl;
        }
        catch(UnsupportedEncodingException ex){
            ex.printStackTrace();
        }

        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 15000)
                .responseTimeout(Duration.ofSeconds(120))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(120, TimeUnit.SECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(120, TimeUnit.SECONDS)));

        WebClient client = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();

        WebClient.ResponseSpec responseSpec = client.get()
                .uri(modifiedWeatherUrl)
                .retrieve();

        String webResponse = responseSpec.bodyToMono(String.class).block();
        log.info("web response {}", webResponse);
        return webResponse;
    }
}
