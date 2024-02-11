package com.workintech.s17challenge.controller;

import com.workintech.s17challenge.entity.CoursesValidations;
import com.workintech.s17challenge.model.Course;
import com.workintech.s17challenge.model.HighCourseGpa;
import com.workintech.s17challenge.model.LowCourseGpa;
import com.workintech.s17challenge.model.MediumCourseGpa;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private List<Course> courses;
    private LowCourseGpa lowCourseGpa;
    private MediumCourseGpa mediumCourseGpa;
    private HighCourseGpa highCourseGpa;

    public CourseController(LowCourseGpa lowCourseGpa, MediumCourseGpa mediumCourseGpa, HighCourseGpa highCourseGpa) {
        this.lowCourseGpa = lowCourseGpa;
        this.mediumCourseGpa = mediumCourseGpa;
        this.highCourseGpa = highCourseGpa;
        System.out.println("Kangaroo Controller created");
    }

    @PostConstruct
    public void init() {
        this.courses = new ArrayList<>();
        System.out.println("Kangaroo Controller initialized");

    }


    @GetMapping
    public List<Course> getCourses() {
        return this.courses;
    }

    @GetMapping("/{name}")
    public Course getCourses(@PathVariable String name) {
        CoursesValidations.isCourseNotExist(courses, name);
        int index = -1;
        for (Course item : courses) {
            if (item.getName().equals(name)) {
                index = courses.indexOf(item);

            }
        }
        return courses.get(index);
    }

    @PostMapping
    public Course addToCourses(@RequestBody Course course) {
        CoursesValidations.isCourseExist(courses, course);
        courses.add(course);
        int index = courses.indexOf(course);
        return courses.get(index);
    }


}
