package com.example.exam_task_with_spring_boot.controller;

import com.example.exam_task_with_spring_boot.dto.request.CourseRequest;
import com.example.exam_task_with_spring_boot.dto.response.CourseResponse;
import com.example.exam_task_with_spring_boot.models.Course;
import com.example.exam_task_with_spring_boot.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/course")
public class CourseController {

    private  final CourseService courseService;

    @GetMapping
    public List<Course> getAllCourses(){
        return courseService.findALl();
    }

    @GetMapping("{courseId}")
    public Course findById(@PathVariable Long courseId){
        return  courseService.findById(courseId);
    }

    @DeleteMapping("{courseId}")
    public CourseResponse delete(@PathVariable Long courseId){
        return courseService.deleteById(courseId);
    }

    @PostMapping
    public Course create (@RequestBody CourseRequest courseRequest){
        System.out.println("ishtep jatat");
        return courseService.save(courseRequest.getCompanyId(), courseRequest);

    }
}
