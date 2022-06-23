package com.example.exam_task_with_spring_boot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
@PreAuthorize("hasAnyAuthority('STUDENT')")
public class TestController {

    @GetMapping("/hello")
    public String hello(){
        return "I am a student";
    }
}
