package com.microservice.microservicegym.messaging;

import com.microservice.microservicegym.controller.dto.DeleteTrainingRequestDTO;
import com.microservice.microservicegym.controller.dto.TrainingWorkloadResponseDTO;
import com.microservice.microservicegym.controller.interceptor.CustomHttpInterceptor;
import com.microservice.microservicegym.model.Training;
import com.microservice.microservicegym.service.TrainerService;
import com.microservice.microservicegym.service.TrainingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TrainingReceiver {
    private static final Logger logger = LoggerFactory.getLogger(CustomHttpInterceptor.class);

    @Autowired
    private TrainingService trainingService;

    @Autowired
    private TrainerService trainerService;
    @Autowired
    private MongoTemplate mongoTemplate;

//    @JmsListener(destination = "training.delete.queue")
    public void deleteTrainingReceiver(DeleteTrainingRequestDTO req) {
        Training training = trainingService.deleteTraining(req.trainingId());
    }

//    @JmsListener(destination = "training.create.queue")
    public void trainingWorkloadReceiver(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("trainer.user.username").is(username));

        TrainingWorkloadResponseDTO trainingWorkloadResponseDTO = mongoTemplate.findOne(query, TrainingWorkloadResponseDTO.class);
        List<Training> trainings = trainingService.selectTrainerTrainings(username, null, null, null, null);

        if (trainingWorkloadResponseDTO != null) {
            Map<Integer, Map<Integer, String>> newSummary = TrainingWorkloadResponseDTO.generateDateSummary(trainings);
            Update update = new Update();
            update.set("summary", newSummary);

            mongoTemplate.updateFirst(query, update, TrainingWorkloadResponseDTO.class);

            trainingWorkloadResponseDTO.setSummary(newSummary);
            return;
        }

        trainingWorkloadResponseDTO = new TrainingWorkloadResponseDTO();
        trainingWorkloadResponseDTO.setSummary(TrainingWorkloadResponseDTO.generateDateSummary(trainings));
        trainingWorkloadResponseDTO.setTrainer(trainerService.selectTrainerProfileByUsername(username));

        mongoTemplate.insert(trainingWorkloadResponseDTO);
    }
}
