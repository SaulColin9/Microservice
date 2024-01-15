package com.microservice.microservicegym.service;

import com.microservice.microservicegym.model.Trainer;

public interface TrainerService {
    Trainer selectTrainerProfileByUsername(String username);

}
