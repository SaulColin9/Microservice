package com.microservice.microservicegym.cucumberglue;

import com.microservice.microservicegym.controller.dto.DeleteTrainingResponseDTO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteTrainingFailureSteps {

    @LocalServerPort
    private String port;
    private ResponseEntity<DeleteTrainingResponseDTO> lastResponse;
    private String token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJHZW9yZ2UuRm9yZCIsImV4cCI6MTcwNTYwOTAxMiwicCI6InNlY3JldCJ9.UtjikX52f-rLgqRZH6XX1w_PXrsKf7CiAA904mR8mOE";
    @When("the client calls training\\/workload\\/{int}")
    public void theClientCallsTrainingWorkload(int id) {
        String url = "/training/workload/" + id;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.add("authorization", token);

        HttpEntity<String> entity = new HttpEntity<>("body", httpHeaders);
        lastResponse = new RestTemplate().exchange("http://localhost:" + port + url, HttpMethod.DELETE, entity, DeleteTrainingResponseDTO.class);
    }

    @And("receives a message and a null training")
    public void receivesAMessageAndANullTraining() {
        DeleteTrainingResponseDTO actualResponse = lastResponse.getBody();
        assertThat(actualResponse.getMsg()).isEqualTo("No training was found");
    }
}
