package com.example.exam_task_with_spring_boot.mapper;

import com.example.exam_task_with_spring_boot.dto.response.CourseResponse;
import com.example.exam_task_with_spring_boot.models.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseViewMapper {

    public CourseResponse viewCourse(Course course){
        if (course==null){
            return  null;
        }

        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setId(course.getId());
        courseResponse.setCourseName(course.getCourseName());
        courseResponse.setDuration(course.getDuration());
        return  courseResponse;
    }

    public List<CourseResponse> viewCourses( List<Course> courses){
        List<CourseResponse> courseResponses = new ArrayList<>();
        for (Course course: courses){
            courseResponses.add(viewCourse(course));
        }
        return courseResponses;
    }
}
