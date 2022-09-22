package com.example.monitoring.slack.gateway;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SlackGatewayConfig {

    @Bean
    public RestTemplate slackRestTemplate() {
        return new RestTemplateBuilder()
            .defaultHeader(HttpHeaders.ACCEPT, "application/json")
            .setConnectTimeout(Duration.ofSeconds(3))
            .setReadTimeout(Duration.ofSeconds(5))
            .build();
    }
}
