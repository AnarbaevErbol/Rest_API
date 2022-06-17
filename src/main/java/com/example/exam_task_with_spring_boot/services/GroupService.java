package com.example.exam_task_with_spring_boot.services;

import com.example.exam_task_with_spring_boot.dto.request.GroupRequestPUT;
import com.example.exam_task_with_spring_boot.dto.request.GroupSaveRequest;
import com.example.exam_task_with_spring_boot.dto.response.DeleteResponse;
import com.example.exam_task_with_spring_boot.dto.response.GroupResponse;
import com.example.exam_task_with_spring_boot.exceptions.CourseNotFoundException;
import com.example.exam_task_with_spring_boot.models.Course;
import com.example.exam_task_with_spring_boot.models.Group;
import com.example.exam_task_with_spring_boot.repositories.CourseRepository;
import com.example.exam_task_with_spring_boot.repositories.GroupRepisitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepisitory groupRepisitory;
    private final CourseRepository courseRepository;


    public List<GroupResponse> findAll() {
        List<Group> groups = groupRepisitory.findAll();
        List<GroupResponse> groupResponses= new ArrayList<>();

        for (Group group: groups){
            List<Course> courses = group.getCourses();
            List<String> courseNames = new ArrayList<>();
            for (Course course : courses){
                courseNames.add(course.getCourseName());
            }
            groupResponses.add(new GroupResponse(
                    group.getId(),
                    group.getGroupName(),
                    group.getDateOfStart(),
                    group.getDateOfFinish(),
                    courseNames
            ));
        }
        return groupResponses;
    }

    public GroupResponse findById(Long groupId) {
        Group group = groupRepisitory.findById(groupId).orElseThrow(()->
                new CourseNotFoundException(
                        "group with id " + groupId + " not found "
                ));

        List<Course> courses = group.getCourses();
        List<String> courseNames = new ArrayList<>();
        for (Course course : courses){
            courseNames.add(course.getCourseName());
        }

        return new GroupResponse(
                group.getId(),
                group.getGroupName(),
                group.getDateOfStart(),
                group.getDateOfFinish(),
                courseNames
        );
    }

    public DeleteResponse deleteById(Long groupId) {
        boolean exists = groupRepisitory.existsById(groupId);
        if(!exists){
            throw new CourseNotFoundException(
                    "group with id "+ groupId + "not found"
            );

        }
        groupRepisitory.deleteById(groupId);

        return new DeleteResponse(
                "DELETED",
                "Course successfully deleted!"
        );
    }

    public GroupResponse save(List<Long> coursesIDs, GroupSaveRequest groupSaveRequest) {
        List<Course> newCourses = new ArrayList<>();
        List<Course> courses = courseRepository.findAll();
        for (Course course: courses){
            for (Long i: coursesIDs){
                boolean exists = course.getId() == i;
                if (exists){
                    newCourses.add(course);
                }
            }
        }

        Group group = new Group();
        group.setGroupName(groupSaveRequest.getGroupName());
        group.setDateOfStart(groupSaveRequest.getDateOfStart());
        group.setDateOfFinish(groupSaveRequest.getDateOfFinish());

        for (Course course: newCourses){
            course.addGroup(group);
        }
        group.setCourses(newCourses);

        groupRepisitory.save(group);


        List<Course> courseList = group.getCourses();
        List<String> courseNamess = new ArrayList<>();
        for (Course course : courseList){
            courseNamess.add(course.getCourseName());
        }

        return new GroupResponse(
                group.getId(),
                group.getGroupName(),
                group.getDateOfStart(),
                group.getDateOfFinish(),
                courseNamess
        );

    }

    @Transactional
    public GroupResponse update(Long groupId, GroupRequestPUT groupRequestPUT) {
        Group groupp = groupRepisitory.findById(groupId).orElseThrow(()->
                new CourseNotFoundException(
                        "group with id " + groupId + " not found "
                ));

        String newName = groupRequestPUT.getGroupName();
        String currentName = groupp.getGroupName();
        if (newName!=null&& !currentName.equals(newName)){
            groupp.setGroupName(newName);
        }

        String newDTS = groupRequestPUT.getDateOfStart();
        String currentDTS = groupp.getDateOfStart();
        if (newDTS!=null && !newDTS.equals(currentDTS)){
            groupp.setDateOfStart(newDTS);
        }

        String newDTF = groupRequestPUT.getDateOfFinish();
        String currentDTF = groupp.getDateOfFinish();
        if (newDTF!=null && !newDTF.equals(currentDTF)){
            groupp.setDateOfFinish(newDTF);
        }

        List<Course> courseList = groupp.getCourses();
        List<String> courseNamess = new ArrayList<>();
        for (Course course : courseList){
            courseNamess.add(course.getCourseName());
        }

        return new GroupResponse(
                groupp.getId(),
                groupp.getGroupName(),
                groupp.getDateOfStart(),
                groupp.getDateOfFinish(),
                courseNamess
        );
    }
}
