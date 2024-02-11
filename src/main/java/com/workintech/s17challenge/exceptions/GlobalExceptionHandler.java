package com.workintech.s17challenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CourseException.class)
    public ResponseEntity<GlobalExceptionResponse> handleCourseException(CourseException courseException) {
        GlobalExceptionResponse globalExceptionResponse =
                new GlobalExceptionResponse(courseException.getStatus().value(),
                        courseException.getMessage(),
                        LocalDateTime.now());
        return new ResponseEntity<>(globalExceptionResponse, courseException.getStatus());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalExceptionResponse> handleException(Exception exception) {
        GlobalExceptionResponse errorResponse =
                new GlobalExceptionResponse(HttpStatus.BAD_REQUEST.value(),
                        exception.getMessage(),
                        LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
