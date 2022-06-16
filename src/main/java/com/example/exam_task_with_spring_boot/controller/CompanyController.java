package com.example.exam_task_with_spring_boot.controller;

import com.example.exam_task_with_spring_boot.dto.request.CompanySaveRequest;
import com.example.exam_task_with_spring_boot.dto.response.CompanyResponse;
import com.example.exam_task_with_spring_boot.models.Company;
import com.example.exam_task_with_spring_boot.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/company")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public Company save(@RequestBody CompanySaveRequest companyRequest){
        return companyService.save(companyRequest);
    }

    @PutMapping("{id}")
    public Company update(@PathVariable Long id, @RequestBody CompanySaveRequest companyRequest){
        return companyService.update(id, companyRequest);
    }

    @GetMapping("{id}")
    public Company findById(@PathVariable Long id){
        return companyService.findById(id);
    }

    @DeleteMapping("{id}")
    public CompanyResponse delete(@PathVariable Long id){
        return companyService.deleteById(id);
    }

    @GetMapping
    public List<Company> getAllCompanies(){
        return companyService.getAllCompanies();
    }

}
