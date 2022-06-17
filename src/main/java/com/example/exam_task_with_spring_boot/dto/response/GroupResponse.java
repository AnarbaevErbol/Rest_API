package com.example.exam_task_with_spring_boot.dto.response;

import com.example.exam_task_with_spring_boot.models.Course;
import com.example.exam_task_with_spring_boot.models.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GroupResponse {
    private Long id;
    private String groupName;
    private String dateOfStart;
    private String dateOfFinish;
    private List<String> coursesName;
    private List<Student> students;

    public GroupResponse(Long id, String groupName, String dateOfStart, String dateOfFinish, List<String> coursesName) {
        this.id = id;
        this.groupName = groupName;
        this.dateOfStart = dateOfStart;
        this.dateOfFinish = dateOfFinish;
        this.coursesName = coursesName;
    }
}
