package com.example.exam_task_with_spring_boot.mapper;

import com.example.exam_task_with_spring_boot.dto.response.CompanyResponse;
import com.example.exam_task_with_spring_boot.models.Company;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyViewMapper {

    public CompanyResponse viewCompany(Company company){
        if (company==null){
            return null;
        }

        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setId(company.getId());
        companyResponse.setCompanyName(company.getCompanyName());
        companyResponse.setLocatedCountry(company.getLocatedCountry());
        companyResponse.setCreated(company.getCreated());

        return companyResponse;
    }

    public List<CompanyResponse> viewCompanies(List<Company> companies){
        List<CompanyResponse> responses = new ArrayList<>();
        for (Company company: companies){
            responses.add(viewCompany(company));
        }
        return responses;
    }

}
