package com.example.exam_task_with_spring_boot.services;

import com.example.exam_task_with_spring_boot.dto.request.CompanyRequest;
import com.example.exam_task_with_spring_boot.dto.response.CompanyResponse;
import com.example.exam_task_with_spring_boot.mapper.CompanyEditMapper;
import com.example.exam_task_with_spring_boot.mapper.CompanyViewMapper;
import com.example.exam_task_with_spring_boot.models.Company;
import com.example.exam_task_with_spring_boot.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyEditMapper companyEditMapper;
    private final CompanyViewMapper companyViewMapper;

    public CompanyResponse create(CompanyRequest companyRequest){
        Company company = companyEditMapper.create(companyRequest);
        companyRepository.save(company);
        return companyViewMapper.viewCompany(company);
    }

    public CompanyResponse update(Long id, CompanyRequest companyRequest){
        Company company = companyRepository.findById(id).get();
        companyEditMapper.update(company, companyRequest);
        return companyViewMapper.viewCompany(companyRepository.save(company));
    }

    public CompanyResponse findById(Long id){
        Company company = companyRepository.findById(id).get();
        return companyViewMapper.viewCompany(company);
    }

    public CompanyResponse deleteById(Long id){
        Company company = companyRepository.getById(id);
        companyRepository.delete(company);
        return companyViewMapper.viewCompany(company);
    }

    public List<CompanyResponse> getAllCompanies(){
        return companyViewMapper.viewCompanies(companyRepository.findAll());
    }



}
