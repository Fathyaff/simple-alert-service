package com.example.monitoring.slack.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.monitoring.constant.UriConstant;
import com.example.monitoring.slack.gateway.SlackGateway;
import com.example.monitoring.slack.dto.request.SlackMonitoringAlertCmd;
import com.example.monitoring.slack.dto.response.SlackMonitoringAlertDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(UriConstant.DEFAULT_URI)
public class SlackMonitoringAlertController {

    private final SlackGateway slackGateway;

    @PostMapping(path = UriConstant.SLACK_ALERT)
    public SlackMonitoringAlertDto alert(@RequestBody SlackMonitoringAlertCmd cmd) {

        slackGateway.sendSlackMessage(cmd.toSlackMessageCmd());

        return SlackMonitoringAlertDto.builder().success(true).build();
    }
}
