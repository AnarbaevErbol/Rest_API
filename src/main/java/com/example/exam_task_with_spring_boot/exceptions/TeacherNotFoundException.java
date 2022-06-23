package com.example.exam_task_with_spring_boot.exceptions;

public class TeacherNotFoundException extends RuntimeException{
    public TeacherNotFoundException() {
    }

    public TeacherNotFoundException(String message) {
        super(message);
    }
}
