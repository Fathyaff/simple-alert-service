package com.example.monitoring.slack.controller;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.monitoring.slack.gateway.SlackGateway;
import com.example.monitoring.slack.dto.request.SlackMonitoringAlertCmd;

class SlackMonitoringAlertControllerTest {

    @InjectMocks
    private SlackMonitoringAlertController controller;

    @Mock
    private SlackGateway slackGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void givenCmd_whenAlertSlack_shouldCallSlackGateway() {
        SlackMonitoringAlertCmd cmd = SlackMonitoringAlertCmd.valueOf("message", "user");
        controller.alert(cmd);

        verify(slackGateway).sendSlackMessage(cmd.toSlackMessageCmd());
    }


}
