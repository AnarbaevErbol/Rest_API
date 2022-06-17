package com.example.exam_task_with_spring_boot.controller;

import com.example.exam_task_with_spring_boot.dto.request.GroupRequestPUT;
import com.example.exam_task_with_spring_boot.dto.request.GroupSaveRequest;
import com.example.exam_task_with_spring_boot.dto.response.DeleteResponse;
import com.example.exam_task_with_spring_boot.dto.response.GroupResponse;
import com.example.exam_task_with_spring_boot.models.Group;
import com.example.exam_task_with_spring_boot.services.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/group")
public class GroupController {


    private final GroupService groupService;

    @GetMapping
    public List<GroupResponse> findAll(){
        return groupService.findAll();
    }

    @GetMapping("{groupId}")
    public GroupResponse findById(@PathVariable Long groupId){
        return groupService.findById(groupId);
    }

    @DeleteMapping("{groupId}")
    public DeleteResponse deleteById(@PathVariable Long groupId){
        return groupService.deleteById(groupId);
    }

    @PostMapping
    public GroupResponse create(@RequestBody GroupSaveRequest groupSaveRequest){
       return groupService.save(groupSaveRequest.getCoursesIDs(), groupSaveRequest);
    }

    @PutMapping("{groupId}")
    public GroupResponse update(@PathVariable Long groupId, @RequestBody GroupRequestPUT groupRequestPUT){
        return groupService.update(groupId, groupRequestPUT);
    }
}
