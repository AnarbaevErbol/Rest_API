package com.example.exam_task_with_spring_boot.mapper;

import com.example.exam_task_with_spring_boot.dto.request.CourseRequest;
import com.example.exam_task_with_spring_boot.models.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseEditMapper {

    public Course create (CourseRequest courseRequest){
        if (courseRequest==null){
            return null;
        }

        Course course = new Course();
        course.setCourseName(courseRequest.getCourseName());
        course.setDuration(courseRequest.getDuration());
         return  course;
    }

    public void update ( Course course, CourseRequest courseRequest){
        course.setCourseName(courseRequest.getCourseName());
        course.setDuration(courseRequest.getDuration());
    }
}
