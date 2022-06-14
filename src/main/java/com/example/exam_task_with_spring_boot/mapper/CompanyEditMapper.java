package com.example.exam_task_with_spring_boot.mapper;

import com.example.exam_task_with_spring_boot.dto.request.CompanyRequest;
import com.example.exam_task_with_spring_boot.models.Company;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CompanyEditMapper {

    public Company create(CompanyRequest companyRequest){
        if (companyRequest == null){
            return null;
        }

        Company company =new Company();
        company.setCompanyName(companyRequest.getCompanyName());
        company.setLocatedCountry(companyRequest.getLocatedCountry());
        company.setCreated(LocalDate.now());

        return company;
    }

    public void update (Company company, CompanyRequest companyRequest){
        company.setCompanyName(companyRequest.getCompanyName());
        company.setLocatedCountry(companyRequest.getLocatedCountry());
    }
}
