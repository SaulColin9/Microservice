package com.microservice.microservicegym.cucumberglue;

import com.microservice.microservicegym.controller.dto.TrainingWorkloadResponseDTO;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class TrainingWorkloadFailure {
    @LocalServerPort
    private String port;
    private ResponseEntity<TrainingWorkloadResponseDTO> lastResponse;
    private String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJHZW9yZ2UuRm9yZCIsImV4cCI6MTcwNTYwOTAxMiwicCI6InNlY3JldCJ9.UtjikX52f-rLgqRZH6XX1w_PXrsKf7CiAA904mR8mOE";

    @When("the client calls \\/training\\/workload\\/John.Doe")
    public void theClientCallsTrainingWorkloadJohnDoe() {
        String url = "/training/workload/John.Doe";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.add("authorization", token);

        HttpEntity<String> entity = new HttpEntity<>("body", httpHeaders);
        System.out.println("http://localhost:" + port + url);
        lastResponse = new RestTemplate().exchange("http://localhost:" + port + url, HttpMethod.POST, entity, TrainingWorkloadResponseDTO.class);
    }

    @Then("the client receives null trainer and empty summary")
    public void theClientReceivesNullTrainerAndEmptySummary() {
        TrainingWorkloadResponseDTO actualResponse = lastResponse.getBody();
        assertThat(actualResponse.getTrainer()).isNull();
        assertThat(actualResponse.getSummary()).isEmpty();
    }

}
