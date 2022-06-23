package com.example.exam_task_with_spring_boot.services;

import com.example.exam_task_with_spring_boot.dto.request.CourseSaveRequest;
import com.example.exam_task_with_spring_boot.dto.request.CourseRequestPUT;
import com.example.exam_task_with_spring_boot.dto.response.DeleteResponse;
import com.example.exam_task_with_spring_boot.dto.response.CourseResponse;
import com.example.exam_task_with_spring_boot.exceptions.CompanyNotFoundException;
import com.example.exam_task_with_spring_boot.exceptions.CourseNotFoundException;
import com.example.exam_task_with_spring_boot.exceptions.GroupNotFoundException;
import com.example.exam_task_with_spring_boot.models.Company;
import com.example.exam_task_with_spring_boot.models.Course;
import com.example.exam_task_with_spring_boot.models.Group;
import com.example.exam_task_with_spring_boot.repositories.CompanyRepository;
import com.example.exam_task_with_spring_boot.repositories.CourseRepository;
import com.example.exam_task_with_spring_boot.repositories.GroupRepisitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;
    private final GroupRepisitory groupRepisitory;

    public List<CourseResponse> findALl() {
        List<Course> courses = courseRepository.findAll();
        List<CourseResponse> simpleResponses = new ArrayList<>();

        for (Course course: courses){
            simpleResponses.add(new CourseResponse(
                    course.getId(),
                    course.getCourseName(),
                    course.getDuration(),
                    course.getCompany().getCompanyName()
            ));
        }


        return simpleResponses;
    }

    public CourseResponse findById(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() ->
                new CourseNotFoundException(
                        "course with id " + courseId + " not found "
                ));

        return new CourseResponse(
                course.getId(),
                course.getCourseName(),
                course.getDuration(),
                course.getCompany().getCompanyName()
        );
    }

    public DeleteResponse deleteById(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() ->
                new CourseNotFoundException(
                        "course with id " + courseId + " not found "
                ));

       if (course.getGroups().size()==1){
           for (Group group: course.getGroups()){
                Group group1 = groupRepisitory.findById(group.getId()).orElseThrow(() ->
                       new GroupNotFoundException(
                               "group with id " + group.getId() + " not found!"));
               groupRepisitory.delete(group1);
            }

        }

//       List<Group> courseGroups = new ArrayList<>();
//       if(course.getGroups().size()>1){
//            for (Group group: course.getGroups()) {
//                   course.removeGroup(group);
//                   groupRepisitory.delete(group );
//            }
//       }
        courseRepository.delete(course);


        return new DeleteResponse(
                "DELETED",
                "Course successfully deleted!"
        );
    }

    public CourseResponse save(Long companyId, CourseSaveRequest courseRequest) {
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

        courseRepository.save(course);

        return new CourseResponse(
                course.getId(),
                course.getCourseName(),
                course.getDuration(),
                course.getCompany().getCompanyName()
        );

    }
    @Transactional
    public CourseResponse update(Long courseId, CourseRequestPUT courseRequest) {
        Course course = courseRepository.findById(courseId).orElseThrow(() ->
                new CourseNotFoundException(
                        "course with id " + courseId + " not found"
                ));

        String newName = courseRequest.getCourseName();
        String currentName = course.getCourseName();
        if (newName!=null && !newName.equals(currentName)){
            course.setCourseName(newName);
        }

        String newDuration =  courseRequest.getDuration();
        String currentDuration = course.getDuration();
        if (newDuration!=null&&!newDuration.equals(currentDuration)){
            course.setDuration(newDuration);
        }


        return new CourseResponse(
                course.getId(),
                course.getCourseName(),
                course.getDuration(),
                course.getCompany().getCompanyName()
        );

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
