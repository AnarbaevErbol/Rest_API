package com.example.exam_task_with_spring_boot.controller;

import com.example.exam_task_with_spring_boot.dto.request.CourseSaveRequest;
import com.example.exam_task_with_spring_boot.dto.request.CourseRequestPUT;
import com.example.exam_task_with_spring_boot.dto.response.DeleteResponse;
import com.example.exam_task_with_spring_boot.dto.response.CourseResponse;
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
    public List<CourseResponse> getAllCourses(){
        return courseService.findALl();
    }

    @GetMapping("{courseId}")
    public CourseResponse findById(@PathVariable Long courseId){
        return  courseService.findById(courseId);
    }

    @DeleteMapping("{courseId}")
    public DeleteResponse delete(@PathVariable Long courseId){
        return courseService.deleteById(courseId);
    }

    @PostMapping
    public CourseResponse create (@RequestBody CourseSaveRequest courseRequest){
        System.out.println("ishtep jatat");
        return courseService.save(courseRequest.getCompanyId(), courseRequest);

    }

    @PutMapping("{courseId}")
    public CourseResponse update(@PathVariable Long courseId, @RequestBody CourseRequestPUT courseRequest){
        return courseService.update(courseId, courseRequest);
    }
}
