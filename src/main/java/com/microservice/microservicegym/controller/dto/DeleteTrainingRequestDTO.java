package com.microservice.microservicegym.controller.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record DeleteTrainingRequestDTO(int trainingId) {

}
