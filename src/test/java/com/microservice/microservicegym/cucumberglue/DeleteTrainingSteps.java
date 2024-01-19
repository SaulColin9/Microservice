package com.microservice.microservicegym.cucumberglue;

import com.microservice.microservicegym.controller.dto.DeleteTrainingResponseDTO;
import com.microservice.microservicegym.controller.dto.TrainingWorkloadResponseDTO;
import com.microservice.microservicegym.dao.jpa.JpaDaoTrainingImpl;
import com.microservice.microservicegym.entitiesFactory.EntitiesFactory;
import com.microservice.microservicegym.model.Training;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.assertj.core.api.AssertionsForClassTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.parser.Entity;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteTrainingSteps {
    @LocalServerPort
    private String port;
    private ResponseEntity<DeleteTrainingResponseDTO> lastResponse;
    @Autowired
    private String token;
    @Autowired
    private JpaDaoTrainingImpl jpaDaoTraining;


    @When("the client calls training\\/workload\\/id")
    public void theClientCallsTrainingWorkload() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.add("authorization", token);

        EntitiesFactory entitiesFactory = new EntitiesFactory();
        Training training = entitiesFactory.createNewTraining();
        training.setId(111);
        jpaDaoTraining.update(111,training);
        String url = "/training/workload/" + training.getId();

        HttpEntity<String> entity = new HttpEntity<>("body", httpHeaders);
        lastResponse = new RestTemplate().exchange("http://localhost:" + port + url, HttpMethod.DELETE, entity, DeleteTrainingResponseDTO.class);
    }

    @Then("the client receives a status code of {int} when deleted")
    public void theClientReceivesAStatusCodeOf(int statusCode) {
        HttpStatusCode actualStatusCode = lastResponse.getStatusCode();
        assertThat(actualStatusCode).isEqualTo(HttpStatusCode.valueOf(statusCode));
    }

    @And("the deleted training is returned")
    public void theDeletedTraining() {
        DeleteTrainingResponseDTO actualResponse = lastResponse.getBody();
        assertThat(actualResponse).isNotNull();
    }

    @When("the client calls endpoint training\\/workload\\/{int}")
    public void theClientCallsEndpointTrainingWorkload(int id) {
        String url = "/training/workload/" + id;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.add("authorization", token);

        HttpEntity<String> entity = new HttpEntity<>("body", httpHeaders);
        try {

            lastResponse = new RestTemplate().exchange("http://localhost:" + port + url, HttpMethod.DELETE, entity, DeleteTrainingResponseDTO.class);
        } catch (HttpClientErrorException e) {
        }
    }

    @Then("receives a message and a null training")
    public void receivesAMessageAndANullTraining() {
        DeleteTrainingResponseDTO actualResponse = lastResponse.getBody();
        AssertionsForClassTypes.assertThat(actualResponse.getMsg()).isEqualTo("No training was found");
    }
}
