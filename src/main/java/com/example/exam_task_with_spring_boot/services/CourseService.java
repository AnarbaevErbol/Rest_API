package com.example.exam_task_with_spring_boot.services;

import com.example.exam_task_with_spring_boot.dto.request.CourseRequest;
import com.example.exam_task_with_spring_boot.dto.response.CourseResponse;
import com.example.exam_task_with_spring_boot.mapper.CourseEditMapper;
import com.example.exam_task_with_spring_boot.mapper.CourseViewMapper;
import com.example.exam_task_with_spring_boot.models.Course;
import com.example.exam_task_with_spring_boot.repositories.CourserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourserRepository courserRepository;
    private final CourseEditMapper courseEditMapper;
    private final CourseViewMapper courseViewMapper;

    public CourseResponse create(CourseRequest courseRequest){
        Course course = courseEditMapper.create(courseRequest);
        courserRepository.save(course);
        return courseViewMapper.viewCourse(course);
    }

    public CourseResponse update(Long id, CourseRequest courseRequest){
        Course course = courserRepository.findById(id).get();
        courseEditMapper.update(course, courseRequest);
        return courseViewMapper.viewCourse(courserRepository.save(course));
    }

    public CourseResponse findById(Long id){
        Course course = courserRepository.findById(id).get();
        return courseViewMapper.viewCourse(course);
    }

    public CourseResponse deleteById(Long id){
        Course course = courserRepository.getById(id);
        courserRepository.delete(course);
        return courseViewMapper.viewCourse(course);
    }

    public List<CourseResponse> findALl (){
        return  courseViewMapper.viewCourses(courserRepository.findAll());
    }
}
