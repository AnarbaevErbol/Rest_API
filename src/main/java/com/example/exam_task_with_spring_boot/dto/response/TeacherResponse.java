package com.example.exam_task_with_spring_boot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TeacherResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String courseName;

}
