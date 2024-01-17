package com.microservice.microservicegym.cucumberglue;

import com.microservice.microservicegym.controller.dto.TrainingWorkloadResponseDTO;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class CucumberTestSteps {
    @LocalServerPort
    private String port;
    private ResponseEntity<TrainingWorkloadResponseDTO> lastResponse;
    private String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJHZW9yZ2UuRm9yZCIsImV4cCI6MTcwNTYwOTAxMiwicCI6InNlY3JldCJ9.UtjikX52f-rLgqRZH6XX1w_PXrsKf7CiAA904mR8mOE";

    @When("the client calls \\/training\\/workload\\/George.Ford")
    public void the_client_calls_training_workload_george_ford() {
        String url = "/training/workload/George.Ford";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.add("authorization", token);

        HttpEntity<String> entity = new HttpEntity<>("body", httpHeaders);
        lastResponse = new RestTemplate().exchange("http://localhost:" + port + url, HttpMethod.POST, entity, TrainingWorkloadResponseDTO.class);
    }

    @Then("the client receives status code of {int}")
    public void thenStatusCode(int statusCode) {
        HttpStatusCode actualStatusCode = lastResponse.getStatusCode();
        assertThat(actualStatusCode).isEqualTo(HttpStatusCode.valueOf(statusCode));

    }
}
