package com.example.exam_task_with_spring_boot.services;

import com.example.exam_task_with_spring_boot.dto.request.TeacherRequestPUT;
import com.example.exam_task_with_spring_boot.dto.request.TeacherSaveRequest;
import com.example.exam_task_with_spring_boot.dto.response.DeleteResponse;
import com.example.exam_task_with_spring_boot.dto.response.TeacherResponse;
import com.example.exam_task_with_spring_boot.exceptions.CourseNotFoundException;
import com.example.exam_task_with_spring_boot.exceptions.TeacherNotFoundException;
import com.example.exam_task_with_spring_boot.models.Course;
import com.example.exam_task_with_spring_boot.models.Teacher;
import com.example.exam_task_with_spring_boot.repositories.CourseRepository;
import com.example.exam_task_with_spring_boot.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public List<TeacherResponse> findAll() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherResponse> teacherResponse = new ArrayList<>();

        for(Teacher teacher:teachers){
            teacherResponse.add(new TeacherResponse(
                    teacher.getId(),
                    teacher.getFirstName(),
                    teacher.getLastName(),
                    teacher.getEmail(),
                    teacher.getCourse().getCourseName()
            ));
        }
        return teacherResponse;

    }

    public TeacherResponse findById(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() ->
                new TeacherNotFoundException(
                        "teacher with id " + teacherId + " not found"
                ));
        return new TeacherResponse(
                teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getEmail(),
                teacher.getCourse().getCourseName()
        );
    }

    public DeleteResponse deleteById(Long teacherId) {
        boolean exists = teacherRepository.existsById(teacherId);
        if(!exists){
            throw new TeacherNotFoundException(
                    "teacher with id "+ teacherId+ " does not exists"
            );
        }
        teacherRepository.deleteById(teacherId);

        return new DeleteResponse(
                "DELETED",
                "Teacher successfully deleted!"
        );
    }

    public TeacherResponse save(Long courseId, TeacherSaveRequest teacherSaveRequest) {

        Course course = courseRepository.findById(courseId).orElseThrow(() ->
                new CourseNotFoundException(
                        "course with id " + courseId + "does not exists"
                ));

        if (course.getTeacher()!=null){
            throw new CourseNotFoundException(
                    "This course "+ course.getCourseName()+ " already have teacher!"
            );
        }

        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherSaveRequest.getFirstName());
        teacher.setLastName(teacherSaveRequest.getLastName());
        teacher.setEmail(teacherSaveRequest.getEmail());
        teacher.setCourseId(teacherSaveRequest.getCourseId());

        course.setTeacher(teacher);
        teacher.setCourse(course);

        teacherRepository.save(teacher);

        return new TeacherResponse(
                teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getEmail(),
                teacher.getCourse().getCourseName()
        );
    }

    @Transactional
    public TeacherResponse update(Long teacherId, TeacherRequestPUT teacherRequestPUT) {

        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() ->
                new TeacherNotFoundException(
                        "teacher with id " + teacherId + " not found"
                ));

        if(teacherRequestPUT.getFirstName()!=null &&
                !teacherRequestPUT.getFirstName().equals(teacher.getFirstName())){
            teacher.setFirstName(teacherRequestPUT.getFirstName());
        }
        if(teacherRequestPUT.getLastName()!=null &&
                !teacherRequestPUT.getLastName().equals(teacher.getLastName())){
            teacher.setLastName(teacherRequestPUT.getLastName());
        }

        if(teacherRequestPUT.getEmail()!=null &&
                !teacherRequestPUT.getEmail().equals(teacher.getEmail())){
            teacher.setEmail(teacherRequestPUT.getEmail());
        }
        return new TeacherResponse(
                teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getEmail(),
                teacher.getCourse().getCourseName()
        );

    }
}
