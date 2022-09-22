package com.example.monitoring.slack.gateway;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value(staticConstructor = "valueOf")
public class SlackMessageCmd {

    SlackPayload text;

    String channel;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SlackPayload {

        private String text;
    }
}
