package com.example.exam_task_with_spring_boot.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupSaveRequest {

    private String groupName;
    private String dateOfStart;
    private String dateOfFinish;
    private List<Long> coursesIDs;
}
