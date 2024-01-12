package com.microservice.microservicegym.service;

import com.microservice.microservicegym.model.Training;

import java.util.Date;
import java.util.List;

public interface TrainingService {

    Training selectTrainingProfile(int id);

    Training deleteTraining(int id);

    List<Training> selectTrainerTrainings(String username, Date periodFrom, Date periodTo, String traineeName,
                                          Integer trainingType);

}
