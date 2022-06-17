package com.example.exam_task_with_spring_boot.repositories;

import com.example.exam_task_with_spring_boot.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepisitory extends JpaRepository<Group, Long> {
}
