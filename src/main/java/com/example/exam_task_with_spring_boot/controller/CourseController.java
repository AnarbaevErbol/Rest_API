package com.example.exam_task_with_spring_boot.controller;

import com.example.exam_task_with_spring_boot.dto.request.CourseRequest;
import com.example.exam_task_with_spring_boot.dto.response.CourseResponse;
import com.example.exam_task_with_spring_boot.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/course")
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public CourseResponse create(@RequestBody CourseRequest courseRequest){
        return courseService.create(courseRequest);
    }

    @PutMapping("{id}")
    public CourseResponse update(@PathVariable Long id, @RequestBody CourseRequest courseRequest){
        return courseService.update(id, courseRequest);
    }

    @GetMapping("{id}")
    public CourseResponse findById(@PathVariable Long id){
        return courseService.findById(id);
    }

    @DeleteMapping("{id}")
    public CourseResponse delete(@PathVariable Long id){
        return courseService.deleteById(id);
    }

    @GetMapping
    public List<CourseResponse> findAll(){
        return courseService.findALl();
    }
}
