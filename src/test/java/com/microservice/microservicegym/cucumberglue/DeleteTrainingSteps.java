package com.microservice.microservicegym.cucumberglue;

import com.microservice.microservicegym.controller.dto.DeleteTrainingResponseDTO;
import com.microservice.microservicegym.controller.dto.TrainingWorkloadResponseDTO;
import com.microservice.microservicegym.model.Training;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteTrainingSteps {
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

    @Then("the client receives a status code of {int}")
    public void theClientReceivesAStatusCodeOf(int statusCode) {
        HttpStatusCode actualStatusCode = lastResponse.getStatusCode();
        assertThat(actualStatusCode).isEqualTo(HttpStatusCode.valueOf(statusCode));
    }

    @And("the deleted training is returned")
    public void theDeletedTraining() {
        DeleteTrainingResponseDTO actualResponse = lastResponse.getBody();
        assertThat(actualResponse).isNotNull();
    }
}
