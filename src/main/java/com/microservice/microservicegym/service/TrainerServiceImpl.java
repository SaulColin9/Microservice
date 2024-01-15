package com.microservice.microservicegym.service;

import com.microservice.microservicegym.dao.Dao;
import com.microservice.microservicegym.dao.jpa.JpaDaoTrainerImpl;
import com.microservice.microservicegym.model.Trainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainerServiceImpl implements TrainerService {
    @Autowired
    private Dao<Trainer> trainerDao;
    private static final Logger logger = LoggerFactory.getLogger(TrainingServiceImpl.class);

    @Override
    public Trainer selectTrainerProfileByUsername(String username) {

        Optional<Trainer> foundTrainer = ((JpaDaoTrainerImpl) trainerDao).getByUsername(username);

        logger.info("Selecting Trainer Profile with username {}", username);

        return foundTrainer.orElse(null);
    }
}
