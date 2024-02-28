package com.workintech.s17challenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @SuppressWarnings("null")
    @ExceptionHandler(CourseException.class)
    public ResponseEntity<GlobalExceptionResponse> handleCourseException(CourseException courseException) {
        log.error("Course exception is occurred", courseException.getMessage());
        GlobalExceptionResponse globalExceptionResponse = new GlobalExceptionResponse(
                courseException.getStatus().value(),
                courseException.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(globalExceptionResponse, courseException.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalExceptionResponse> handleException(Exception exception) {
        log.error("Unexpected error occurred", exception.getMessage());
        GlobalExceptionResponse errorResponse = new GlobalExceptionResponse(HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
