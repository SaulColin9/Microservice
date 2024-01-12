package com.microservice.microservicegym.controller.dto;

import com.microservice.microservicegym.model.Training;

public class DeleteTrainingResponseDTO {
    public DeleteTrainingResponseDTO(String msg, Training training) {
        this.msg = msg;
        this.training = training;
    }

    private String msg;
    private Training training;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }
}
