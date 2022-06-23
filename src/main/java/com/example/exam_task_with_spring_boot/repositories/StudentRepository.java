package com.example.exam_task_with_spring_boot.repositories;

import com.example.exam_task_with_spring_boot.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
