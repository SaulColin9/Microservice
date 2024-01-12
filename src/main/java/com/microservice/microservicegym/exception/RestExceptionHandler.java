package com.microservice.microservicegym.exception;

import com.microservice.microservicegym.controller.dto.DeleteTrainingResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {UserAuthenticationException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse errorHandlerAuthentication(UserAuthenticationException ex, WebRequest req) {
        String responseString = "Invalid credentials";
        return new ErrorResponse(responseString, "403");
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse errorHandlerIllegalArgument(IllegalArgumentException ex, WebRequest req) {
        String responseString = "Given entity was not found";
        return new ErrorResponse(responseString, "400");
    }

    @ExceptionHandler(value = {UserAuthorizationException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse errorHandlerUserAuthorization(UserAuthorizationException ex, WebRequest req) {
        String responseString = "Provided user is not authorized to perform this action";
        return new ErrorResponse(responseString, "403");
    }

    @ExceptionHandler(value = {UserBlockedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse errorHandlerUserAuthorization(UserBlockedException ex, WebRequest req) {
        String responseString = "You have been blocked for 5 minutes";
        return new ErrorResponse(responseString, "403");
    }

    @ExceptionHandler(value = {NullPointerException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public DeleteTrainingResponseDTO errorHandlerNoEntityFound(NullPointerException ex, WebRequest req) {
        String responseString = "No training was found";
        return new DeleteTrainingResponseDTO(responseString, null);
    }
}
