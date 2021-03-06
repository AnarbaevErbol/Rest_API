package com.example.exam_task_with_spring_boot.models;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String locatedCountry;

    @OneToMany(mappedBy = "company",
              cascade = CascadeType.ALL,
              orphanRemoval = true)
    private List<Course> courses;


    @CreatedDate
    private LocalDate created;

    public void addCourse(Course course){
        this.courses.add(course);
    }


}
