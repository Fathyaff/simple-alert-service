package com.example.monitoring.slack.gateway;

public interface SlackGateway {

    void sendSlackMessage(SlackMessageCmd slackMessageCmd);
}
