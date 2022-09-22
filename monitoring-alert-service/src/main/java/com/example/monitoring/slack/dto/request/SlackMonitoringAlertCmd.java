package com.example.monitoring.slack.dto.request;

import com.example.monitoring.slack.gateway.SlackMessageCmd;
import lombok.Value;

@Value(staticConstructor = "valueOf")
public class SlackMonitoringAlertCmd {

    String message;

    String channel;

    public SlackMessageCmd toSlackMessageCmd() {
        return SlackMessageCmd.valueOf(
            SlackMessageCmd.SlackPayload.builder().text(message).build(),
            channel
        );
    }
}
