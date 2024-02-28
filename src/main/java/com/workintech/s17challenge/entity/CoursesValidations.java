package com.workintech.s17challenge.entity;

import com.workintech.s17challenge.exceptions.CourseException;
import com.workintech.s17challenge.model.Course;
import org.springframework.http.HttpStatus;

import java.util.List;

public class CoursesValidations {

    private static final String COURSE_EXIST = "Course with given name is exist: ";
    private static final String COURSE_NOT_EXIST = "Course with given name is not exist: ";
    private static final String BAD_ID = "Id can not be null or less then zero";

    public static void isCourseExist(List<Course> courses, Course course) {
        for (Course item : courses) {
            if (item.getName().equals(course.getName())) {
                throw new CourseException(COURSE_EXIST + course.getName(), HttpStatus.CONFLICT);
            }
        }
    }

    public static void isCourseNotExist(List<Course> courses, String name) {
        boolean exist = false;
        for (Course item : courses) {
            if (item.getName().equals(name)) {
                exist = true;
                break;
            }
        }

        if (!exist) {
            throw new CourseException(COURSE_NOT_EXIST + name, HttpStatus.NOT_FOUND);
        }

    }

    public static void checkId(Integer id) {
        if (id == null || id < 0) {
            throw new CourseException(BAD_ID, HttpStatus.BAD_REQUEST);
        }
    }

}
