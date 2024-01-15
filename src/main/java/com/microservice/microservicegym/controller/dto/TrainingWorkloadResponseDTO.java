package com.microservice.microservicegym.controller.dto;

import com.microservice.microservicegym.model.Trainer;
import com.microservice.microservicegym.model.Training;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class TrainingWorkloadResponseDTO {
    private Trainer trainer;
    private Map<Integer, Map<Integer, String>> summary;

    public TrainingWorkloadResponseDTO() {
    }

    public static Map<Integer, Map<Integer, String>> generateDateSummary(List<Training> trainings) {
        List<LocalDate> dates = new ArrayList<>();
        for (Training training : trainings) {
            Date date = training.getTrainingDate();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            dates.add(localDate);
        }

        Map<Integer, Map<Integer, Integer>> dateCounter = new HashMap<>();

        for (LocalDate date : dates) {
            int year = date.getYear();
            int month = date.getMonthValue();

            dateCounter
                    .computeIfAbsent(year, k -> new HashMap<>())
                    .merge(month, 1, Integer::sum);
        }

        Map<Integer, Map<Integer, String>> result = new HashMap<>();

        dateCounter.forEach((year, monthMap) -> {
            Map<Integer, String> monthSummaryMap = new HashMap<>();
            monthMap.forEach((month, count) -> {
                monthSummaryMap.put(month, count + " Days of Training");
            });
            result.put(year, monthSummaryMap);
        });

        return result;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Map<Integer, Map<Integer, String>> getSummary() {
        return summary;
    }

    public void setSummary(Map<Integer, Map<Integer, String>> summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "TrainingWorkloadResponseDTO{" +
                "trainer=" + trainer +
                ", summary=" + summary +
                '}';
    }
}
