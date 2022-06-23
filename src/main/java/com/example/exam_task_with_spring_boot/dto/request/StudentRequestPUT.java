package com.example.exam_task_with_spring_boot.dto.request;

import com.example.exam_task_with_spring_boot.enums.StudyFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequestPUT {

    private String firstName;
    private String lastName;
    private String email;
    private StudyFormat studyFormat;
}
