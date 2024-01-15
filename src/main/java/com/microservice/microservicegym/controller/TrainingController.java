package com.microservice.microservicegym.controller;

import com.microservice.microservicegym.controller.dto.DeleteTrainingResponseDTO;
import com.microservice.microservicegym.controller.dto.TrainingWorkloadResponseDTO;
import com.microservice.microservicegym.model.Training;
import com.microservice.microservicegym.service.TrainerService;
import com.microservice.microservicegym.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/training/workload")
public class TrainingController {
    @Autowired
    private TrainingService trainingService;

    @Autowired
    private TrainerService trainerService;
    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("/{username}")
    public TrainingWorkloadResponseDTO getWorkload(@PathVariable("username") String username) {
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
            return trainingWorkloadResponseDTO;
        }

        trainingWorkloadResponseDTO = new TrainingWorkloadResponseDTO();
        trainingWorkloadResponseDTO.setSummary(TrainingWorkloadResponseDTO.generateDateSummary(trainings));
        trainingWorkloadResponseDTO.setTrainer(trainerService.selectTrainerProfileByUsername(username));

        mongoTemplate.insert(trainingWorkloadResponseDTO);

        return trainingWorkloadResponseDTO;

    }

    @DeleteMapping("/{id}")
    public DeleteTrainingResponseDTO deleteTraining(@PathVariable("id") int id) {
        Training training = trainingService.deleteTraining(id);
        return new DeleteTrainingResponseDTO("OK", training);
    }


}
