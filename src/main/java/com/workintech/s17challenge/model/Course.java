package com.workintech.s17challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class Course {
    private Integer Id;
    private String name;
    private int credit;
    private Grade grade;
}
