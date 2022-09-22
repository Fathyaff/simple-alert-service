package com.example.monitoring.slack.gateway;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "slack")
public class SlackProperties {

    private Map<String, String> channels;
}
