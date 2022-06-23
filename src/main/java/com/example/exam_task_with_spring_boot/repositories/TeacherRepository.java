package com.example.exam_task_with_spring_boot.repositories;

import com.example.exam_task_with_spring_boot.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
