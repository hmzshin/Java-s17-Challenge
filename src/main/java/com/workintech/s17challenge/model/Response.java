package com.workintech.s17challenge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Response {
    private Course course;
    private Integer totalGpa;

}
