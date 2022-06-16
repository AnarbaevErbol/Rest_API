package com.example.exam_task_with_spring_boot.services;

import com.example.exam_task_with_spring_boot.dto.request.CourseRequest;
import com.example.exam_task_with_spring_boot.dto.response.CourseResponse;
import com.example.exam_task_with_spring_boot.exceptions.CompanyNotFoundException;
import com.example.exam_task_with_spring_boot.exceptions.CourseNotFoundException;
import com.example.exam_task_with_spring_boot.models.Company;
import com.example.exam_task_with_spring_boot.models.Course;
import com.example.exam_task_with_spring_boot.repositories.CompanyRepository;
import com.example.exam_task_with_spring_boot.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;

    public List<Course> findALl() {
        return courseRepository.findAll();
    }

    public Course findById(Long courseId) {
       return courseRepository.findById(courseId).orElseThrow(()->
                new CourseNotFoundException(
                        "course with id " + courseId +" not found "
                ));
    }

    public CourseResponse deleteById(Long courseId) {
        boolean exists = courseRepository.existsById(courseId);
        if (!exists){
            throw new CourseNotFoundException(
                    "Course with id " + courseId + " does not exist"
            );
        }

        courseRepository.deleteById(courseId);

        return new CourseResponse(
                "DELETED",
                "Course successfully deleted!"
        );
    }

    public Course save(Long companyId, CourseRequest courseRequest) {
       Company company = companyRepository.findById(companyId).orElseThrow(()->
                new CompanyNotFoundException(
                        "company with id "+companyId+" not found"
                ));

        Course course = new Course();
        course.setCourseName(courseRequest.getCourseName());
        course.setDuration(courseRequest.getDuration());
        course.setCompanyId(courseRequest.getCompanyId());

        company.addCourse(course);
        course.setCompany(company);

         return courseRepository.save(course);


    }

//    private final CourseEditMapper courseEditMapper;
//    private final CourseViewMapper courseViewMapper;
//
//    public CourseResponse create(Long id, CourseRequest courseRequest){
//        Company company = companyRepository.findById(id).get();
//        return courseViewMapper.viewCourse(courserRepository
//                .save(courseEditMapper.create(company,courseRequest)));
//    }
//
//    public CourseResponse update(Long id, CourseRequest courseRequest){
//        Course course = courserRepository.findById(id).get();
//        courseEditMapper.update(course, courseRequest);
//        return courseViewMapper.viewCourse(courserRepository.save(course));
//    }
//
//    public CourseResponse findById(Long id){
//        Course course = courserRepository.findById(id).get();
//        return courseViewMapper.viewCourse(course);
//    }
//
//    public CourseResponse deleteById(Long id){
//        Course course = courserRepository.getById(id);
//        courserRepository.delete(course);
//        return courseViewMapper.viewCourse(course);
//    }

//    public List<CourseResponse> findALl (){
//        return  courseViewMapper.viewCourses(courserRepository.findAll());
//    }
}
