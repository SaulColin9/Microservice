package com.microservice.microservicegym.controller;

import com.microservice.microservicegym.controller.dto.DeleteTrainingRequestDTO;
import com.microservice.microservicegym.controller.dto.DeleteTrainingResponseDTO;
import com.microservice.microservicegym.controller.dto.TrainingWorkloadResponseDTO;
import com.microservice.microservicegym.model.Training;
import com.microservice.microservicegym.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/training/workload")
public class TrainingController {
    @Autowired
    TrainingService trainingService;

    @PostMapping("/{username}")
    public TrainingWorkloadResponseDTO getWorkload(@PathVariable("username") String username) {
        return new TrainingWorkloadResponseDTO(trainingService.selectTrainerTrainings(username, null, null, null, null));

    }

    @DeleteMapping("/{id}")
    public DeleteTrainingResponseDTO deleteTraining(@PathVariable("id") int id) {
        Training training = trainingService.deleteTraining(id);
        return new DeleteTrainingResponseDTO("OK", training);
    }


}
