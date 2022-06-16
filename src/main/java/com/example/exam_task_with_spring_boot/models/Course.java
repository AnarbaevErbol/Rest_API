package com.example.exam_task_with_spring_boot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "course_gen", sequenceName = "course_seq", allocationSize = 1)
    private Long id;
    private String courseName;
    private String duration;

    @Transient
    private Long companyId;

    @ManyToOne(cascade = CascadeType.ALL,
              fetch = FetchType.EAGER)
    @JsonIgnore
    private Company company;



}
