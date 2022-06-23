package com.example.exam_task_with_spring_boot.controller;


import com.example.exam_task_with_spring_boot.dto.request.TeacherRequestPUT;
import com.example.exam_task_with_spring_boot.dto.request.TeacherSaveRequest;
import com.example.exam_task_with_spring_boot.dto.response.DeleteResponse;
import com.example.exam_task_with_spring_boot.dto.response.TeacherResponse;
import com.example.exam_task_with_spring_boot.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/teacher")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public List<TeacherResponse> findAll(){
        return teacherService.findAll();
    }

    @GetMapping("{teacherId}")
    public TeacherResponse findById(@PathVariable Long teacherId){
        return teacherService.findById(teacherId);
    }

    @DeleteMapping("{teacherId}")
    public DeleteResponse delete(@PathVariable Long teacherId){
        return teacherService.deleteById(teacherId);
    }
    @PostMapping
    public TeacherResponse create(@RequestBody TeacherSaveRequest teacherSaveRequest){
        return teacherService.save(teacherSaveRequest.getCourseId(),teacherSaveRequest);
    }

    @PutMapping("{teacherId}")
    public TeacherResponse update(@PathVariable Long teacherId,
                                  @RequestBody TeacherRequestPUT teacherRequestPUT){
        return teacherService.update(teacherId,teacherRequestPUT);
    }

}
