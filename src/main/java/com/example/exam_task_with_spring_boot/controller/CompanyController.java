package com.example.exam_task_with_spring_boot.controller;

import com.example.exam_task_with_spring_boot.dto.request.CompanyRequest;
import com.example.exam_task_with_spring_boot.dto.response.CompanyResponse;
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
    public CompanyResponse create(@RequestBody CompanyRequest companyRequest){
        return companyService.create(companyRequest);
    }

    @PutMapping("{id}")
    public CompanyResponse update(@PathVariable Long id, @RequestBody CompanyRequest companyRequest){
        return companyService.update(id, companyRequest);
    }

    @GetMapping("{id}")
    public CompanyResponse findById(@PathVariable Long id){
        return companyService.findById(id);
    }

    @DeleteMapping("{id}")
    public CompanyResponse delete(@PathVariable Long id){
        return companyService.deleteById(id);
    }

    @GetMapping
    public List<CompanyResponse> getAllCompanies(){
        return companyService.getAllCompanies();
    }

}
