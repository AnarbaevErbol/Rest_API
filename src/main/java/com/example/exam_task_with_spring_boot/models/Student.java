package com.example.exam_task_with_spring_boot.models;

import com.example.exam_task_with_spring_boot.enums.StudyFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "students")
@Getter @Setter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private StudyFormat studyFormat;

    @ManyToOne(cascade = CascadeType.ALL)
    private Group group;

}
