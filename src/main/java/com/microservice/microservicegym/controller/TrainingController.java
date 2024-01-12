package com.microservice.microservicegym.controller;

import com.microservice.microservicegym.controller.dto.DeleteTrainingRequestDTO;
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

    @PostMapping
    public TrainingWorkloadResponseDTO getWorkload() {
        return new TrainingWorkloadResponseDTO(trainingService.selectTrainerTrainings("George.Ford", null, null, null, null));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteTraining(@RequestBody DeleteTrainingRequestDTO req) {
        trainingService.deleteTraining(req.id());
        return ResponseEntity.ok("OK");
    }


}
