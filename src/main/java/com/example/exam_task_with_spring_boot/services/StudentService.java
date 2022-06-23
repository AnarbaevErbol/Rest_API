package com.example.exam_task_with_spring_boot.services;


import com.example.exam_task_with_spring_boot.dto.request.StudentRequestPUT;
import com.example.exam_task_with_spring_boot.dto.request.StudentSaveRequest;
import com.example.exam_task_with_spring_boot.dto.response.DeleteResponse;
import com.example.exam_task_with_spring_boot.dto.response.StudentResponse;
import com.example.exam_task_with_spring_boot.exceptions.GroupNotFoundException;
import com.example.exam_task_with_spring_boot.exceptions.StudentNotFoundException;
import com.example.exam_task_with_spring_boot.models.Group;
import com.example.exam_task_with_spring_boot.models.Student;
import com.example.exam_task_with_spring_boot.repositories.GroupRepisitory;
import com.example.exam_task_with_spring_boot.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepisitory groupRepisitory;

    public List<StudentResponse> findAll() {
        List<Student> students = studentRepository.findAll();
        List<StudentResponse> simpleResponse = new ArrayList<>();

        for (Student student: students){
            simpleResponse.add(new StudentResponse(
                    student.getId(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getEmail(),
                    student.getStudyFormat(),
                    student.getGroup().getGroupName()
            ));

        }
        return simpleResponse;
    }

    public StudentResponse findById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()->
                        new StudentNotFoundException(
                                "Student with id "+ studentId+ " not found!"
                        ));

        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getStudyFormat(),
                student.getGroup().getGroupName()
        );
    }

    public DeleteResponse deleteStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()->
                        new StudentNotFoundException(
                                "Student with id "+ studentId+ " not found!"
                        ));
        studentRepository.deleteById(studentId);

        return new DeleteResponse(
                "DELETED",
                "Student successfully deleted!"
        );
    }

    public StudentResponse save(Long groupId, StudentSaveRequest studentSaveRequest) {
        Group group = groupRepisitory.findById(groupId).orElseThrow(()->
                new GroupNotFoundException(
                        "group with id " + groupId + " not found "
                ));

        Student student = new Student();
        student.setFirstName(studentSaveRequest.getFirstName());
        student.setLastName(studentSaveRequest.getLastName());
        student.setEmail(studentSaveRequest.getEmail());
        student.setStudyFormat(studentSaveRequest.getStudyFormat());
        student.setGroupId(studentSaveRequest.getGroupId());

        group.addStudent(student);
        student.setGroup(group);

        studentRepository.save(student);

        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getStudyFormat(),
                student.getGroup().getGroupName()
        );
    }

    @Transactional
    public StudentResponse update(Long studentId, StudentRequestPUT studentRequestPUT) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()->
                        new StudentNotFoundException(
                                "Student with id "+ studentId+ " not found!"
                        ));
        String newName = studentRequestPUT.getFirstName();
        String curName = student.getFirstName();

        if(newName!=null && !newName.equals(curName)){
            student.setFirstName(newName);
        }
        String newLastName = studentRequestPUT.getLastName();
        String curLastName = student.getLastName();

        if(newLastName!=null && !newLastName.equals(curLastName)){
            student.setLastName(newLastName);
        }
        String newEmail = studentRequestPUT.getEmail();
        String curEmail = student.getEmail();

        if(newEmail!=null && !newEmail.equals(curEmail)){
            student.setEmail(newEmail);
        }

        student.setStudyFormat(studentRequestPUT.getStudyFormat());

        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getStudyFormat(),
                student.getGroup().getGroupName()

        );

    }
}
