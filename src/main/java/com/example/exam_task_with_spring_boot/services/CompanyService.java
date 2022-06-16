package com.example.exam_task_with_spring_boot.services;

import com.example.exam_task_with_spring_boot.dto.request.CompanySaveRequest;
import com.example.exam_task_with_spring_boot.dto.response.CompanyResponse;
import com.example.exam_task_with_spring_boot.exceptions.CompanyNotFoundException;
import com.example.exam_task_with_spring_boot.models.Company;
import com.example.exam_task_with_spring_boot.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Transactional
    public Company update(Long id, CompanySaveRequest companyRequest){
        Company company = getCompanyById(id);

        String newName= companyRequest.getCompanyName();

        if (newName!=null){
            company.setCompanyName(newName);
        }

        String newLocatedCountry = companyRequest.getLocatedCountry();

        if (newLocatedCountry!=null){
            company.setLocatedCountry(newLocatedCountry);
        }

        return company;
    }

    private Company getCompanyById(Long id){
        return companyRepository.findById(id).orElseThrow(()->
                new CompanyNotFoundException(
                        "Company with id" + id + "not found!"
                ));
    }


    public Company findById(Long id){
//        boolean exists = companyRepository.exists(Long id);
//
//        if(!exists){
//            throw new CompanyNotFoundException(
//                    "Company with id" + id + "not found!"
//            );
//        }

        return getCompanyById(id);


    }

    public CompanyResponse deleteById(Long id){

        boolean exists = companyRepository.existsById(id);

        if(!exists){
            throw new CompanyNotFoundException(
                    "Company with id" + id + "not found!"
            );
        }

        companyRepository.deleteById(id);

        return new CompanyResponse(
                "DELETED",
                "successfully deleted company");
    }

    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }


    public Company save(CompanySaveRequest companyRequest) {
        Company company = new Company();
        company.setCompanyName(companyRequest.getCompanyName());
        company.setLocatedCountry(companyRequest.getLocatedCountry());
        company.setCreated(LocalDate.now());

         return companyRepository.save(company);

    }
}
