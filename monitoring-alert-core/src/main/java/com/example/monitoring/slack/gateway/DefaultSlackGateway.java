package com.example.monitoring.slack.gateway;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Service
@RequiredArgsConstructor
public class DefaultSlackGateway implements SlackGateway {

    private static final String WEBHOOK_URL_BASE = "https://hooks.slack.com/services/%s";

    private final RestTemplate slackRestTemplate;

    private final SlackProperties slackProperties;

    @SneakyThrows
    @Override
    public void sendSlackMessage(SlackMessageCmd slackMessageCmd) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String message = new ObjectMapper().writeValueAsString(slackMessageCmd.getText());
        HttpEntity<String> entity = new HttpEntity<>(message, headers);

        String userWebhookUrl = getUserWebhookUrl(slackMessageCmd);
        slackRestTemplate.exchange(userWebhookUrl, HttpMethod.POST, entity, String.class);
    }

    private String getUserWebhookUrl(SlackMessageCmd slackMessageCmd) {
        String userChannelId = slackProperties.getChannels().get(slackMessageCmd.getChannel());
        return String.format(WEBHOOK_URL_BASE, userChannelId);
    }
}
