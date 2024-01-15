package com.microservice.microservicegym.messaging;

import com.microservice.microservicegym.controller.dto.DeleteTrainingRequestDTO;
import com.microservice.microservicegym.controller.dto.TrainingWorkloadResponseDTO;
import com.microservice.microservicegym.controller.interceptor.CustomHttpInterceptor;
import com.microservice.microservicegym.model.Training;
import com.microservice.microservicegym.service.TrainingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class TrainingReceiver {
    private static final Logger logger = LoggerFactory.getLogger(CustomHttpInterceptor.class);

    @Autowired
    TrainingService trainingService;

    @JmsListener(destination = "training.delete.queue")
    public void deleteTrainingReceiver(DeleteTrainingRequestDTO req) {
        Training training = trainingService.deleteTraining(req.trainingId());

    }

    @JmsListener(destination = "training.create.queue")
    public void trainingWorkloadReceiver(String username) {
        logger.info(
                new TrainingWorkloadResponseDTO(trainingService
                        .selectTrainerTrainings(username, null,
                                null, null, null)).toString());
    }
}
