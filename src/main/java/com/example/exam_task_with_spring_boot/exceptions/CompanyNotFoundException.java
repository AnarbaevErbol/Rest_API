package com.example.exam_task_with_spring_boot.exceptions;

public class CompanyNotFoundException extends  RuntimeException{

    public CompanyNotFoundException() {
    }

    public CompanyNotFoundException(String message) {
        super(message);
    }
}
