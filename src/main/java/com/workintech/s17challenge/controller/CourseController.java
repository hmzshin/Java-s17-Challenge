package com.workintech.s17challenge.controller;

import com.workintech.s17challenge.entity.CoursesValidations;
import com.workintech.s17challenge.model.Course;
import com.workintech.s17challenge.model.CourseGpa;
import com.workintech.s17challenge.model.Response;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private List<Course> courses;
    private final CourseGpa lowCourseGpa;
    private final CourseGpa mediumCourseGpa;
    private final CourseGpa highCourseGpa;

    public CourseController(@Qualifier("lowCourseGpa") CourseGpa lowCourseGpa,
            @Qualifier("mediumCourseGpa") CourseGpa mediumCourseGpa,
            @Qualifier("highCourseGpa") CourseGpa highCourseGpa) {
        this.lowCourseGpa = lowCourseGpa;
        this.mediumCourseGpa = mediumCourseGpa;
        this.highCourseGpa = highCourseGpa;
        System.out.println("Course Controller created");
    }

    @PostConstruct
    public void init() {
        this.courses = new ArrayList<>();
        System.out.println("Course Controller initialized");

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
    public ResponseEntity<Response> addToCourses(@RequestBody Course course) {
        CoursesValidations.isCourseExist(courses, course);
        courses.add(course);

        Integer totalGpa = getTotalGpa(course);
        Response response = new Response(course, totalGpa);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Response> updateCourse(@RequestBody Course course, @PathVariable Integer id) {
        CoursesValidations.checkId(id);
        CoursesValidations.isCourseNotExist(courses, course.getName());
        Course courseExist = courses.stream().filter((c) -> c.getId() == id).findAny().get();
        int index = courses.indexOf(courseExist);
        System.out.println("*****************************" + index);
        course.setId(id);
        courses.set(index, course);
        Integer totalGpa = getTotalGpa(course);
        Response response = new Response(course, totalGpa);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    private Integer getTotalGpa(Course course) {

        if (course.getCredit() <= 2) {

            return course.getGrade().getCoefficient() * course.getCredit() * lowCourseGpa.getGpa();

        } else if (course.getCredit() == 3) {

            return course.getGrade().getCoefficient() * course.getCredit() * mediumCourseGpa.getGpa();

        } else {

            return course.getGrade().getCoefficient() * course.getCredit() * highCourseGpa.getGpa();
        }

    }

}
