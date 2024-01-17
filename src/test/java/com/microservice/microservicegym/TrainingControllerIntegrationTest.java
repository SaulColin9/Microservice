package com.microservice.microservicegym;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/", glue = {"com.microservice.microservicegym.cucumberglue"})
public class TrainingControllerIntegrationTest {
}
