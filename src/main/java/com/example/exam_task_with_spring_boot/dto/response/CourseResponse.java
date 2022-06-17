package com.example.exam_task_with_spring_boot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CourseResponse {
    private Long id;
    private String courseName;
    private String duration;
    private String companyName;


}
