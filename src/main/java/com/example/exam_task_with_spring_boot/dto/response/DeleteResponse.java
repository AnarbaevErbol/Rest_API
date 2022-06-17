package com.example.exam_task_with_spring_boot.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeleteResponse {

    private String status;
    private String message;

    public DeleteResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}