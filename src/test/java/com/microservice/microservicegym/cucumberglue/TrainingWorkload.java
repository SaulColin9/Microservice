package com.microservice.microservicegym.cucumberglue;

import com.microservice.microservicegym.controller.dto.TrainingWorkloadResponseDTO;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class TrainingWorkload {
    @LocalServerPort
    private String port;
    private ResponseEntity<TrainingWorkloadResponseDTO> lastResponse;
    @Autowired
    private String token;


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
