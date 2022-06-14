package com.example.exam_task_with_spring_boot.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "company_gen", sequenceName = "company_seq", allocationSize = 1)
    private Long id;
    private String companyName;
    private String locatedCountry;
    @CreatedDate
    private LocalDate created;


}
