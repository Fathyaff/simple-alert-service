package com.example.monitoring.slack.gateway;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

class DefaultSlackGatewayTest {

    @InjectMocks
    private DefaultSlackGateway defaultSlackGateway;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private SlackProperties slackProperties;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void givenCmd_whenSend_shouldCallRestTemplate() {
        when(slackProperties.getChannels()).thenReturn(
            Collections.singletonMap("user", "user-webhook-credentials"));

        SlackMessageCmd cmd = SlackMessageCmd.valueOf(
            SlackMessageCmd.SlackPayload.builder().text("message").build(),
            "user"
        );
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        when(restTemplate.exchange(anyString(),
            eq(HttpMethod.POST),
            eq(new HttpEntity<>(cmd.getText(), httpHeaders)),
            eq(String.class)
        )).thenReturn(ResponseEntity.ok("OK"));

        defaultSlackGateway.sendSlackMessage(cmd);

        String expectedWebhookUrl = "https://hooks.slack.com/services/user-webhook-credentials";
        verify(restTemplate).exchange(
            expectedWebhookUrl,
            HttpMethod.POST,
            new HttpEntity<>(cmd.getText(), httpHeaders),
            String.class
        );
    }

}
