package com.example.monitoring.slack.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.example.monitoring.constant.UriConstant;
import com.example.monitoring.slack.dto.request.SlackMonitoringAlertCmd;
import com.example.monitoring.slack.dto.response.SlackMonitoringAlertDto;
import com.example.monitoring.slack.gateway.SlackGateway;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

@WebMvcTest(SlackMonitoringAlertController.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class SlackMonitoringAlertControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SlackGateway slackGateway;

    private final ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    @Test
    void givenCmd_whenAlertSlack_shouldReturnOk() {
        String uri = String.format("%s%s", UriConstant.DEFAULT_URI, UriConstant.SLACK_ALERT);
        SlackMonitoringAlertCmd cmd = SlackMonitoringAlertCmd.valueOf("Hi", "MonitoringAlert");
        MvcResult mvcResult = mockMvc
            .perform(MockMvcRequestBuilders.post(uri)
                .content(mapper.writeValueAsString(cmd))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        SlackMonitoringAlertDto dto = mapper.readValue(response, SlackMonitoringAlertDto.class);
        assertTrue(dto.isSuccess());
    }
}
