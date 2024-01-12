package com.microservice.microservicegym.service;

import com.microservice.microservicegym.dao.Dao;
import com.microservice.microservicegym.dao.jpa.JpaDaoTrainingImpl;
import com.microservice.microservicegym.model.Training;
import com.microservice.microservicegym.service.utils.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TrainingServiceImpl implements TrainingService {
    @Autowired
    protected Dao<Training> trainingDao;
    @Autowired
    protected Validator validator;
    protected static final Logger logger = LoggerFactory.getLogger(TrainingServiceImpl.class);

    @Override
    public Training selectTrainingProfile(int id) {
        Optional<Training> training = trainingDao.get(id);

        logger.info("Selecting Training Profile with id {}", id);
        return training.orElse(null);
    }

    @Override
    public Training deleteTraining(int id) {
        Optional<Training> training = trainingDao.delete(id);

        logger.info("Deleting Training Profile with id {}", id);
        return training.orElse(null);
    }
    @Override
    public List<Training> selectTrainerTrainings(String username, Date periodFrom, Date periodTo, String traineeName,
                                                 Integer trainingType) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        validator.validateFieldsNotNull(params);

        logger.info("Selecting Trainings with Trainer username {}", username);
        return ((JpaDaoTrainingImpl) trainingDao).getTrainerTrainings(username, periodFrom, periodTo, traineeName, trainingType);
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    public void setTrainingDao(Dao<Training> trainingDao) {
        this.trainingDao = trainingDao;
    }


}
