package com.workintech.s17challenge.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class GlobalExceptionResponse {
    private int status;
    private String message;
    private LocalDateTime createdAt;
}
