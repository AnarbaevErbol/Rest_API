package com.example.exam_task_with_spring_boot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.*;

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

    @ManyToOne(cascade = MERGE)
    @JsonIgnore
    private Company company;

    @ManyToMany(mappedBy = "courses",cascade = {PERSIST,MERGE},fetch = FetchType.LAZY)
    private List<Group> groups;

    @OneToOne(mappedBy = "course",orphanRemoval = true)
    private Teacher teacher;

    public void addGroup(Group group){
        this.groups.add(group);
    }

    public void removeGroup(Group group) {
        this.groups.remove(group);
        group.getCourses().remove(this);
    }


}
