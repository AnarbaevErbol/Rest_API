package com.example.exam_task_with_spring_boot.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseResponse {

    private Long id;
    private String courseName;
    private String duration;
}
