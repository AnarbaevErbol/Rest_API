package com.example.exam_task_with_spring_boot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    private String duration;

    @Transient
    private Long companyId;

    @ManyToOne(cascade = CascadeType.ALL,
              fetch = FetchType.EAGER)
    @JsonIgnore
    private Company company;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    private List<Group> groups;

    @OneToOne(mappedBy = "course",cascade = CascadeType.ALL, orphanRemoval = true)
    private Teacher teacher;

    public void addGroup(Group group){
        this.groups.add(group);
    }





}
