package com.example.exam_task_with_spring_boot.dto.response;

import com.example.exam_task_with_spring_boot.models.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CourseResponse {

    private String status;
    private String message;

    public CourseResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}