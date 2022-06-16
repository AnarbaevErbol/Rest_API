package com.example.exam_task_with_spring_boot.exceptions;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException() {
    }

    public CourseNotFoundException(String message) {
        super(message);
    }
}
