package com.example.exam_task_with_spring_boot.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupRequestPUT {
    private String groupName;
    private String dateOfStart;
    private String dateOfFinish;
}
