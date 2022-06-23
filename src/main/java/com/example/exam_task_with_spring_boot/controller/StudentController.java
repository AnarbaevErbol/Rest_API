package com.example.exam_task_with_spring_boot.controller;

import com.example.exam_task_with_spring_boot.dto.request.StudentRequestPUT;
import com.example.exam_task_with_spring_boot.dto.request.StudentSaveRequest;
import com.example.exam_task_with_spring_boot.dto.response.DeleteResponse;
import com.example.exam_task_with_spring_boot.dto.response.StudentResponse;
import com.example.exam_task_with_spring_boot.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/student")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<StudentResponse> findAll(){
        return studentService.findAll();
    }

    @GetMapping("{studentId}")
    public StudentResponse findById(@PathVariable Long studentId){
        return studentService.findById(studentId);
    }

    @DeleteMapping("{studentId}")
    public DeleteResponse delete(@PathVariable Long studentId){
        return studentService.deleteStudentById(studentId);
    }

    @PostMapping
    public StudentResponse save(@RequestBody StudentSaveRequest studentSaveRequest){
        return studentService.save(studentSaveRequest.getGroupId(), studentSaveRequest);
    }

    @PutMapping("{studentId}")
    public StudentResponse update(@PathVariable Long studentId,
                                  @RequestBody StudentRequestPUT studentRequestPUT){

        return studentService.update(studentId,studentRequestPUT);

    }

}
